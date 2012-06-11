package ru.sstu.word_biblio.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * <code>Installer</code> class is used to install Bibliography script.
 *
 * @author denis_murashev
 */
public class Installer {

	private static final String DESTINATION_PATH
			= "%s\\%s\\Bibliography\\Style\\gost.xsl";

	private static final int BUFFER_SIZE = 16 * 1024;

	/**
	 * Installs Bibliography script.
	 *
	 * @param officeDir directory where MS Office is installed
	 * @param version   MS Office version
	 * @throws IOException if some error occurs
	 */
	public void install(File officeDir, OfficeVersion version)
			throws IOException {
		FileOutputStream output = null;
		try {
			InputStream input = getClass().getResourceAsStream("/GOST.xslt");
			ReadableByteChannel srcChannel = Channels.newChannel(input);
			String destPath = String.format(DESTINATION_PATH,
					officeDir.getPath(), version.getFolderName());
			output = new FileOutputStream(destPath);
			FileChannel destChannel = output.getChannel();
			final ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
			while (srcChannel.read(buffer) != -1) {
				buffer.flip();
				destChannel.write(buffer);
				buffer.compact();
			}
			buffer.flip();
			while (buffer.hasRemaining()) {
				destChannel.write(buffer);
			}
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}
}
