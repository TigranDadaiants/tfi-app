package ru.sstu.exam.core;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * <code>TestPaperFormatter</code> class can format output for test papers.
 *
 * @author Denis_Murashev
 * @since Exam 1.0
 */
public class TestPaperFormatter {

	private static final String ERROR_FORMAT
			= "Cannot provide formatted output";

	/**
	 * Writes formatted test papers to given writer.
	 *
	 * @param writer     writer
	 * @param testPapers list of test papers
	 * @throws ExamException if some error occurs
	 */
	public void format(PrintWriter writer, List<TestPaper> testPapers)
			throws ExamException {
		Reader reader = new InputStreamReader(getClass()
				.getResourceAsStream("/output.vm"));
		VelocityContext context = new VelocityContext();
		context.put("testPapers", testPapers);
		try {
			Velocity.evaluate(context, writer, "", reader);
		} catch (ParseErrorException e) {
			throw new ExamException(ERROR_FORMAT);
		} catch (MethodInvocationException e) {
			throw new ExamException(ERROR_FORMAT);
		} catch (ResourceNotFoundException e) {
			throw new ExamException(ERROR_FORMAT);
		} catch (IOException e) {
			throw new ExamException(ERROR_FORMAT);
		}
	}
}
