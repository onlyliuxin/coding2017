package week03.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import week03.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	String targetPath;

	public DownloadThread(Connection conn, int startPos, int endPos) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	public DownloadThread(Connection conn, int startPos, int endPos, String targetPath) {
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
		this.targetPath = targetPath;
	}

	public void run() {
		try {
			System.out.println("线程" + this.getName() + "开始下载. startPos:" + startPos + "; endPos:" + endPos);
			byte[] rs = conn.read(startPos, endPos);
			RandomAccessFile raf = new RandomAccessFile(targetPath, "rw");
			raf.seek(startPos);
			raf.write(rs, 0, rs.length);
			raf.close();
			System.out.println("线程" + this.getName() + "下载完成.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
