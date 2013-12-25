package ru.sstu.vec.core.service.impl;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import ru.sstu.docs.Block;
import ru.sstu.docs.Document;
import ru.sstu.docs.DocumentException;
import ru.sstu.docs.DocumentReader;
import ru.sstu.docs.Image;
import ru.sstu.docs.Node;
import ru.sstu.docs.TextNode;
import ru.sstu.vec.core.dao.LabDao;
import ru.sstu.vec.core.dao.LabVariantDao;
import ru.sstu.vec.core.dao.PictureDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.LabVariant;
import ru.sstu.vec.core.domain.Picture;
import ru.sstu.vec.core.service.LabImporter;

/**
 * {@code AbstractLabImporter} class is most common implementation of lab
 * importer.
 *
 * @author Denis_Murashev
 * @since VEC 2.0
 */
abstract class AbstractLabImporter implements LabImporter {

	private static final Set<String> PREFIX_VARIANT = new HashSet<String>();

	static {
		PREFIX_VARIANT.add("\u0412");
		PREFIX_VARIANT.add("V");
	}

	@Resource
	private LabDao labDao;

	@Resource
	private LabVariantDao labVariantDao;

	@Resource
	private PictureDao pictureDao;

	@Transactional
	@Override
	public Lab importLab(Course course, InputStream input)
			throws DocumentException {
		DocumentReader reader = getReader();
		Document document = reader.read(input);
		int size = document.size();
		if (size == 0) {
			throw new DocumentException("The lab document is empty.");
		}
		String name = document.get(0).getText();
		Lab lab = labDao.find(course, name);
		if (lab == null) {
			lab = new Lab();
			lab.setCourse(course);
			lab.setName(name);
		} else {
			labVariantDao.delete(lab);
		}
		saveText(document, lab);
		saveImages(reader.getImages(), lab);
		return lab;
	}

	/**
	 * @return concrete document reader
	 */
	protected abstract DocumentReader getReader();

	/**
	 * Extracts markup from document node.
	 *
	 * @param node document node
	 * @return markup if there is any
	 */
	private String extractMarkup(Node node) {
		String name = node.getText();
		if (name.startsWith("[")) {
			int index = name.indexOf("]");
			if (index != -1) {
				return name.substring(1, index);
			}
		}
		if (name.startsWith("<")) {
			int index = name.indexOf(">");
			if (index != -1) {
				return name.substring(1, index);
			}
		}
		int index = name.indexOf(":");
		if (index != -1) {
			return name.substring(0, index);
		}
		return null;
	}

	/**
	 * Ignores given markup in given node.
	 *
	 * @param node   node to change
	 * @param markup markup to ignore
	 * @return node with ignored prefix
	 */
	private Node ignorePrefix(Node node, String markup) {
		int ignore = node.getText().indexOf(markup) + markup.length() + 1;
		if (!(node instanceof Block<?>)) {
			TextNode textNode = (TextNode) node;
			textNode.setText(node.getText().substring(ignore));
			return textNode;
		}
		int index = 0;
		Block<?> block = (Block<?>) node;
		while (ignore > 0 && index < block.size()) {
			TextNode textNode = (TextNode) block.get(index);
			String text = textNode.getText();
			if (ignore < text.length()) {
				text = text.substring(ignore);
				textNode.setText(text.trim());
				ignore = 0;
			} else {
				textNode.setText("");
				ignore -= text.length();
			}
			index++;
		}
		return block;
	}

	/**
	 * Saves lab text.
	 *
	 * @param document document
	 * @param lab      lab
	 */
	private void saveText(Document document, Lab lab) {
		StringBuilder text = new StringBuilder();
		LabVariant variant = null;
		int index = 0;
		int size = document.size();
		for (int i = 1; i < size; i++) {
			Node node = document.get(i);
			String markup = extractMarkup(node);
			if (PREFIX_VARIANT.contains(markup)) {
				if (variant == null) {
					lab.setText(text.toString());
					labDao.save(lab);
				} else {
					variant.setText(text.toString());
					labVariantDao.save(variant);
				}
				variant = new LabVariant();
				variant.setLab(lab);
				index++;
				variant.setIndex(index);
				text = new StringBuilder();
				text.append(ignorePrefix(node, markup));
			} else {
				text.append(node);
			}
		}
		if (variant == null) {
			lab.setText(text.toString());
			labDao.save(lab);
		} else {
			variant.setText(text.toString());
			labVariantDao.save(variant);
		}
	}

	/**
	 * Saves images.
	 *
	 * @param images images to save
	 * @param lab    lab
	 */
	private void saveImages(Collection<Image> images, Lab lab) {
		List<LabVariant> variants = labVariantDao.find(lab);
		final String delimeter = "-";
		StringBuilder builder = new StringBuilder();
		builder.append(lab.getCourse().getId()).append(delimeter);
		builder.append(lab.getId()).append(delimeter);
		String prefix = builder.toString();
		for (Image i : images) {
			Picture picture = new Picture();
			picture.setName(prefix + i.getUrl());
			int index = i.getUrl().lastIndexOf(".");
			if (index != -1) {
				picture.setType(i.getUrl().substring(index + 1).toUpperCase());
			}
			picture.setData(i.getData());
			picture.setLab(lab);
			pictureDao.save(picture);
			lab.setText(correctText(lab.getText(), i.getUrl(),
					picture.getId()));
			for (LabVariant v : variants) {
				v.setText(correctText(v.getText(), i.getUrl(),
						picture.getId()));
			}
		}
		labDao.save(lab);
		for (LabVariant v : variants) {
			labVariantDao.save(v);
		}
	}

	/**
	 * Corrects images URLs in text.
	 *
	 * @param text text
	 * @param name image name
	 * @param id   image id
	 * @return corrected text
	 */
	private String correctText(String text, String name, long id) {
		final String endOfUrl = "\"";
		final String imageTag = "<img src=\"" + name + endOfUrl;
		StringBuilder builder = new StringBuilder(text);
		int index = builder.indexOf(imageTag);
		while (index != -1) {
			String replacement = "<img src=\"../image?id=" + id + endOfUrl;
			builder.replace(index, index + imageTag.length(), replacement);
			index = builder.indexOf(imageTag, index + imageTag.length() + 1);
		}
		return builder.toString();
	}
}
