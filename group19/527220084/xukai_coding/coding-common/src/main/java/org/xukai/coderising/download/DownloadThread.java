package org.xukai.coderising.download;


import org.xukai.coderising.download.api.Connection;
import org.xukai.coderising.download.api.ConnectionException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.Semaphore;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	private String rootPath = System.getProperty("user.dir");

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){
		FileOutputStream outputStream = null;
		FileChannel channel = null;
		try {

			File file1 = new File(rootPath + "/image/阳光暖妹.jpg");
			if (!file1.exists()) {
				file1.createNewFile();
			}
			RandomAccessFile file = new RandomAccessFile(file1, "rwd");
			outputStream= new FileOutputStream(file1);
			byte[] buff = conn.read(startPos, endPos);
			channel = outputStream.getChannel();
			synchronized (DownloadThread.class){

				channel.position(startPos);
				ByteBuffer wrap = ByteBuffer.wrap(buff);
				channel.write(wrap);
				if (startPos != 0) {
//					file.seek(startPos);
				}
//				file.write(buff);
				System.out.println(file.length());
//				file.close();
			}

		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("wancheng");
//				outputStream.close();
				channel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
