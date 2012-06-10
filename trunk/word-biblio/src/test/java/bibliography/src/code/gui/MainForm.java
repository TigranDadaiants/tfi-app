package code.gui;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.nio.channels.FileChannel;

import code.Search;

public class MainForm extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel caption = new JLabel();
	private JLabel yes = new JLabel();
	private JLabel no = new JLabel();
	private JButton open = new JButton("Open");
	private JButton close = new JButton("Close");
	JRadioButton off2010;
	JRadioButton off2007;
	FileChannel srcChannel;
	Search sFile;
	long flag;
	String version;

	public MainForm(FileChannel src) throws IOException {
		super("Office Bibliography");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setBounds(300, 250, 420, 200);
		this.setResizable(false);
		this.srcChannel = src;
		sFile = new Search();
		flag = 0;
		version = "office12";

		this.addWindowListener(new WindowListener() {

			public void windowClosing(WindowEvent event) {
					System.exit(0);
			}

			public void windowActivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowOpened(WindowEvent e) {
			}

		});

		this.setLayout(null);
		this.setBackground(new Color(200, 200, 200));
		
		caption.setText("Выберете путь к программе Microsoft Office.");
		caption.setBounds(10, 5, 350, 20);
		caption.setFont(new Font("Arial", Font.PLAIN, 14));
		this.add(caption);

		caption = new JLabel();
		caption.setText("Нажмите Open, выберете папку и снова нажмите Open");
		caption.setBounds(10, 25, 380, 20);
		caption.setFont(new Font("Arial", Font.PLAIN, 14));
		this.add(caption);
		
		caption = new JLabel();
		caption.setText("Важно! Office должен быть версии не позже 2007!");
		caption.setBounds(10, 50, 350, 20);
		caption.setFont(new Font("Arial", Font.BOLD, 12));
		this.add(caption);
		
		caption = new JLabel();
		caption.setText("Какой версии у Вас Office?");
		caption.setBounds(100, 75, 350, 25);
		caption.setFont(new Font("Arial", Font.PLAIN, 14));
		this.add(caption);
		off2007 = new JRadioButton("2007");
		off2007.setActionCommand("office12");
		off2007.setSelected(true);
		off2007.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				version="office12";
			}
		});
		
	    off2010 = new JRadioButton("2010");
	    off2010.setActionCommand("office14");
	    off2010.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				version="office14";
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
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});
		
		open.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				no.setText("");
				yes.setText("");
				
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = chooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					if (selectedFile.isDirectory()) {

						String catalog = selectedFile.getPath()	+ "\\"+version+"\\Bibliography\\Style\\WRX.xsl";
						try {
							flag = sFile.search(srcChannel, catalog);
							if(flag != 0){	
								open.setEnabled(false);
								off2010.setEnabled(false);
								off2007.setEnabled(false);
								no.setText("УСТАНОВКА ЗАВЕРШЕНА!");
								yes.setText("Данный компонент добавлен в MS Word.");
								close.setVisible(true) ;
							} else {
								no.setText("ОШИБКА УСТАНОВКИ!");
								yes.setText("Неправильно выбрана версия программы или папка.");
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}

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

	}
}
