package com.coderising.download;

import java.io.FileOutputStream;
import java.io.IOException;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	byte[] result;

	public DownloadThread( Connection conn, int startPos, int endPos, byte[] result){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.result = result;
		
	}
	public void run(){
		try {
			byte[] download = this.conn.read(this.startPos, this.endPos);
			//synchronized(this.result){
				System.arraycopy(download, 0, this.result, this.startPos, download.length);
				System.out.println(this.startPos+"   "+this.endPos);
			//}
			FileOutputStream fos = new FileOutputStream("C:\\b.jpg");
			fos.write(this.result);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
