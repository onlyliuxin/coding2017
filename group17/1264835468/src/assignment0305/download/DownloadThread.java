package assignment0305.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.concurrent.CountDownLatch;

import assignment0305.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	RandomAccessFile file;
	CountDownLatch latch;

	public DownloadThread(Connection conn, RandomAccessFile file, int startPos, int endPos, CountDownLatch latch) {
		this.conn = conn;
		this.file = file;
		this.startPos = startPos;
		this.endPos = endPos;
		this.latch = latch;
	}

	public void run() {
		try {
			byte[] data = conn.read(startPos, endPos);
			FileChannel fileChannel = file.getChannel();
			MappedByteBuffer buffer = fileChannel.map(MapMode.READ_WRITE, startPos, endPos - startPos + 1);
			buffer.put(data);
			latch.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
