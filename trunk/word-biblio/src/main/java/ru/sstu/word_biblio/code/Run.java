package ru.sstu.word_biblio.code;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import ru.sstu.word_biblio.code.gui.MainForm;

public class Run {

	public static void main(String[] args) throws IOException {
		InputStream input = Run.class.getResourceAsStream("/GOST.xslt");
		ReadableByteChannel srcChannel = Channels.newChannel(input);
		MainForm form = new MainForm(srcChannel); 
		form.setVisible(true);
	}
}

