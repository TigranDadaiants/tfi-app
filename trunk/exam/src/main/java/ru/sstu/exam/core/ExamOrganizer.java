package ru.sstu.exam.core;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <code>ExamOrganizer</code> class.
 *
 * @author Denis_Murashev
 * @since Exam 1.0
 */
public class ExamOrganizer {

	private String data;

	/**
	 * Initializes organizer with given data.
	 *
	 * @param data data
	 */
	public ExamOrganizer(String data) {
		this.data = data;
	}

	/**
	 * Writes organized questions to given print writer.
	 *
	 * @param writer print writer
	 * @throws ExamException if some error occurs
	 */
	public void save(PrintWriter writer) throws ExamException {
		StringTokenizer tokenizer = new StringTokenizer(data, "\n");
		List<String> list = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			token = token.replaceAll("^\\d*[.)]?", "");
			token = token.trim();
			if (!"".equals(token)) {
				list.add(token);
			}
		}
		Collections.shuffle(list);
		final int itemsCount = 2;
		int papersCount = list.size() / itemsCount;
		List<TestPaper> testPapers = new ArrayList<TestPaper>(papersCount);
		for (int i = 0; i < papersCount; i++) {
			TestPaper paper = new TestPaper();
			paper.setIndex(i + 1);
			List<String> items = new ArrayList<String>(itemsCount);
			for (int j = 0; j < itemsCount; j++) {
				items.add(list.get(2 * i + j));
			}
			paper.setItems(items);
			testPapers.add(paper);
		}
		new TestPaperFormatter().format(writer, testPapers);
	}
}
