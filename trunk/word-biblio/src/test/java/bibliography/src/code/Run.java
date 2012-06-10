package code;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import code.gui.MainForm;

public class Run {
	public static void main(String[] args) throws IOException {

		File file = new File(System.getProperty("user.dir")	+ "\\src\\code\\xsl\\WRX.xsl");

		FileInputStream src = new FileInputStream(file);
		FileChannel srcChannel = src.getChannel();
		long flag = 0;


		if (flag == 0){
			MainForm form = new code.gui.MainForm(srcChannel); 
			form.setVisible(true);
		} else 
			System.out.println("GooD!");
	}

}

