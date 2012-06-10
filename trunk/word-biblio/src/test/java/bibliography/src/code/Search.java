package code;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Search {
	
	long flag;
	FileChannel destChannel;
	
	public Search() throws IOException {
		destChannel = null;
		flag = 0;
		
	}

	public long search(FileChannel srcChannel, String catalog) throws IOException {

		try {
			FileOutputStream diskC = new FileOutputStream(catalog);
			destChannel = diskC.getChannel();
			flag = srcChannel.transferTo(0, srcChannel.size(), destChannel);
			return this.flag;
		} catch (java.io.FileNotFoundException e) {
			// System.err.println(e.toString());
		}
		return this.flag;
	}
}