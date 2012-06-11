package ru.sstu.word_biblio.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import ru.sstu.word_biblio.core.Installer;
import ru.sstu.word_biblio.core.OfficeVersion;

public class MainForm extends JFrame {

	private static final long serialVersionUID = -1958433664555002205L;

	private static final Font FONT_NORMAL = new Font("Arial", Font.PLAIN, 14);

	private JLabel caption = new JLabel();
	private JLabel yes = new JLabel();
	private JLabel no = new JLabel();
	private JButton open = new JButton("Open");
	private JButton close = new JButton("Close");
	private JRadioButton off2010;
	private JRadioButton off2007;
	private JFileChooser chooser;
	private ResourceBundle bundle;
	private OfficeVersion version = OfficeVersion.OFFICE_2007;

	public MainForm() {
		super("Office Bibliography");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(300, 250, 420, 200);
		this.setResizable(false);

		this.setLayout(null);
		this.setBackground(new Color(200, 200, 200));

		bundle = ResourceBundle.getBundle("message");
		caption.setText(bundle.getString("comment.select"));
		caption.setBounds(10, 5, 350, 20);
		caption.setFont(FONT_NORMAL);
		this.add(caption);

		caption = new JLabel();
		caption.setText(bundle.getString("comment.press"));
		caption.setBounds(10, 25, 380, 20);
		caption.setFont(FONT_NORMAL);
		this.add(caption);
		
		caption = new JLabel();
		caption.setText(bundle.getString("comment.important"));
		caption.setBounds(10, 50, 350, 20);
		caption.setFont(new Font("Arial", Font.BOLD, 12));
		this.add(caption);
		
		caption = new JLabel();
		caption.setText(bundle.getString("comment.question"));
		caption.setBounds(100, 75, 350, 25);
		caption.setFont(FONT_NORMAL);
		this.add(caption);
		off2007 = new JRadioButton("2007");
		off2007.setActionCommand("office12");
		off2007.setSelected(true);
		off2007.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				version = OfficeVersion.OFFICE_2007;
			}
		});
		
	    off2010 = new JRadioButton("2010");
	    off2010.setActionCommand("office14");
	    off2010.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				version = OfficeVersion.OFFICE_2010;
			}
		});
	    
	    ButtonGroup group = new ButtonGroup();
	    group.add(off2007);
	    group.add(off2010);
	    off2007.setBounds(120, 100, 70, 20);
	    off2010.setBounds(190, 100, 70, 20);
	    this.add(off2007);
	    this.add(off2010);
	    
	    

		open.setBounds(5, 90, 80, 25);
		open.setEnabled(true);
		
		close.setBounds(5, 130, 80, 25);
		close.setEnabled(true);
		close.setVisible(true);
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainForm.this.dispose();
			}
		});
		
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				install();
			}
		});
		this.add(open);
		this.add(close);

		no = new JLabel();
		no.setBounds(100, 130, 480, 15);
		no.setFont(new Font("Arial", Font.BOLD, 12));
		this.add(no);
		
		yes = new JLabel();
		yes.setBounds(100, 145, 480, 25);
		yes.setFont(new Font("Arial", Font.PLAIN, 12));
		this.add(yes);

		String rootPath = System.getenv().get("ProgramFiles");
		chooser = new JFileChooser(rootPath);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		setVisible(true);
	}

	private void install() {
		no.setText("");
		yes.setText("");

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			if (selectedFile.isDirectory()) {
				try {
					new Installer().install(selectedFile, version);
					open.setEnabled(false);
					off2010.setEnabled(false);
					off2007.setEnabled(false);
					no.setText(bundle.getString("result.success"));
					yes.setText(bundle.getString("result.success.description"));
					close.setVisible(true) ;
				} catch (IOException e) {
					no.setText(bundle.getString("result.failure"));
					yes.setText(bundle.getString("result.failure.description"));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new MainForm(); 
	}
}
