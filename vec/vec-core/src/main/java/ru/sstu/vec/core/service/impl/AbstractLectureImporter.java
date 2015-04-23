package ru.sstu.vec.core.service.impl;

import java.io.InputStream;
import java.util.Collection;

import javax.annotation.Resource;

import ru.sstu.docs.Document;
import ru.sstu.docs.DocumentException;
import ru.sstu.docs.DocumentReader;
import ru.sstu.docs.Image;
import ru.sstu.docs.Node;
import ru.sstu.vec.core.dao.LectureDao;
import ru.sstu.vec.core.dao.PictureDao;
import ru.sstu.vec.core.domain.Course;
import ru.sstu.vec.core.domain.Lecture;
import ru.sstu.vec.core.domain.Picture;
import ru.sstu.vec.core.service.LectureImporter;

public abstract class AbstractLectureImporter implements LectureImporter {

    @Resource
    private LectureDao lectureDao;

    @Resource
    private PictureDao pictureDao;

    /**
     * @return concrete document reader
     */
    protected abstract DocumentReader getReader();

    @Override
    public Lecture importLecture(Course course, InputStream input)
            throws DocumentException {
        DocumentReader reader = getReader();
        Document document = reader.read(input);
        int size = document.size();
        if (size == 0) {
            throw new DocumentException("The lab document is empty.");
        }
        String name = document.get(0).getText();
        Lecture lecture = lectureDao.find(course, name);
        if (lecture == null) {
            lecture = new Lecture();
            lecture.setCourse(course);
            lecture.setName(name);
        }
        saveText(document, lecture);
        saveImages(reader.getImages(), lecture);
        return lecture;
    }

    /**
     * Saves lecture text.
     *
     * @param document
     *            document
     * @param lecture
     *            lecture
     */
    private void saveText(Document document, Lecture lecture) {
        StringBuilder text = new StringBuilder();
        int size = document.size();
        for (int i = 1; i < size; i++) {
            Node node = document.get(i);
            text.append(node);
        }
        lecture.setText(text.toString());
        lectureDao.save(lecture);
    }

    /**
     * Saves images.
     *
     * @param images
     *            images to save
     * @param lecture
     *            lecture
     */
    private void saveImages(Collection<Image> images, Lecture lecture) {
        final String delimeter = "-";
        StringBuilder builder = new StringBuilder();
        builder.append(lecture.getCourse().getId()).append(delimeter);
        builder.append(lecture.getId()).append(delimeter);
        String prefix = builder.toString();
        for (Image i : images) {
            Picture picture = new Picture();
            picture.setName(prefix + i.getUrl());
            int index = i.getUrl().lastIndexOf(".");
            if (index != -1) {
                picture.setType(i.getUrl().substring(index + 1).toUpperCase());
            }
            picture.setData(i.getData());
            picture.setLecture(lecture);
            pictureDao.save(picture);
            lecture.setText(correctText(lecture.getText(), i.getUrl(),
                    picture.getId()));
        }
        lectureDao.save(lecture);
    }

    /**
     * Corrects images URLs in text.
     *
     * @param text
     *            text
     * @param name
     *            image name
     * @param id
     *            image id
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
