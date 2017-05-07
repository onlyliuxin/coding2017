package com.coderising.download;

import com.coderising.download.api.Connection;

import java.io.*;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread(Connection conn, int startPos, int endPos){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		System.out.println(startPos + "---->" + endPos);
	}
	public void run(){
		try {
			File file = new File("test.jpg");
			RandomAccessFile out = null;
			if (file != null) {
				out = new RandomAccessFile(file,"rwd");
			}

			byte[] buffer = new byte[1024];
		/*	out.seek(startPos);
			out.write(conn.read(startPos,endPos));*/
			InputStream in = conn.getHttpURLConnection().getInputStream();
			in.skip(startPos);
			int len = 0;
			while ((len = in.read(buffer)) != 1) {
				if (len < 0) {
					break;
				}else {
					//System.out.println("len length"+len);
					out.write(buffer, 0, len);
				}
			}

			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
