package ru.sstu.exam.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ru.sstu.exam.core.ExamException;
import ru.sstu.exam.core.ExamOrganizer;

/**
 * <code>MainFrame</code> class represents main application frame.
 *
 * @author Denis_Murashev
 * @since Exam 1.0
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 7594004499158705318L;

	private static final int FRAME_WIDTH = 800;

	private static final int FRAME_HEIGHT = 600;

	private JFileChooser fileChooser = new JFileChooser(".");

	private JEditorPane editor = new JEditorPane();

	/**
	 * Initializes frame.
	 */
	public MainFrame() {
		super("Exam Organizer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		editor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(editor);
		JMenuItem saveItem = new JMenuItem("Save...");
		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this)
						== JFileChooser.APPROVE_OPTION) {
					saveFile(fileChooser.getSelectedFile());
				}
			}
		});
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.dispose();
			}
		});
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(saveItem);
		fileMenu.add(exitItem);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		pack();
		setVisible(true);
	}

	private void saveFile(File fileName) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName);
			new ExamOrganizer(editor.getText()).save(writer);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(MainFrame.this,
					"Cannot save your data.");
		} catch (ExamException e) {
			JOptionPane.showMessageDialog(MainFrame.this,
					"Cannot format your data.");
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * @param args application arguments
	 */
	public static void main(String[] args) {
		new MainFrame();
	}
}
