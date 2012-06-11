package ru.sstu.word_biblio.code;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class Search {

	public void search(ReadableByteChannel srcChannel, String catalog)
			throws IOException {
		FileOutputStream diskC = new FileOutputStream(catalog);
		FileChannel destChannel = diskC.getChannel();
		final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
		while (srcChannel.read(buffer) != -1) {
			// prepare the buffer to be drained
			buffer.flip();
			// write to the channel, may block
			destChannel.write(buffer);
			// If partial transfer, shift remainder down
			// If buffer is empty, same as doing clear()
			buffer.compact();
		}
		// EOF will leave buffer in fill state
		buffer.flip();
		// make sure the buffer is fully drained.
		while (buffer.hasRemaining()) {
			destChannel.write(buffer);
		}
	}
}
