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
	 */
	public void save(PrintWriter writer) {
		StringTokenizer tokenizer = new StringTokenizer(data, "\n");
		List<String> list = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			list.add(tokenizer.nextToken());
		}
		Collections.shuffle(list);
		final int items = 2;
		int count = list.size() / items;
		writer.print("<html>");
		writer.print("<head></head>");
		writer.print("<body>");
		writer.print("<table width='100%'>");
		for (int i = 0; i < count; i++) {
			writer.print("<tr><td style='border: 1px solid #333333;'>");
			writer.print("<h1># " + (i + 1) + "</h1>");
			writer.print("<ol>");
			for (int j = 0; j < items; j++) {
				writer.print("<li>" + list.get(2 * i + j) + "</li>");
			}
			writer.print("</ol>");
			writer.print("</td></tr>");
		}
		writer.print("</table>");
		writer.print("</body>");
		writer.print("</html>");
	}
}
