package com.coderising.download;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	private RandomAccessFile tempFile = null;
	public DownloadThread( Connection conn, RandomAccessFile tempFile,String treadName){
		super.setName(treadName);
		this.conn = conn;	
		this.startPos = conn.getStartPos();
		this.endPos = conn.getEndPos();
		this.tempFile = tempFile;
	}
	public void run(){	
		
		byte buf[] = null;
		int count = (endPos - startPos)/1024;
		int seekPos = 0;
		try {
			for (int i = 1; i < count; i++) {	
				System.out.println(this.getName() + "    : " + (startPos+ 1024*(i-1)) + "-------" + (startPos + 1024*i) );
				buf = new byte[1024];
				conn.read(buf);
				seekPos = startPos+ 1024*(i-1);
				if (0 != seekPos) {
					seekPos--;
				}
				tempFile.seek(seekPos);
				writeToFile(buf);
				buf = null;
			}
			
			System.out.println(this.getName()  + "    : " + (startPos+ 1024*(count-1)) + "-------	" + (endPos) );
			buf = new byte[endPos-(startPos+ 1024*(count-1))];
			conn.read(buf);
			seekPos = startPos+ 1024*(count-1)-1;
			tempFile.seek(seekPos);
			writeToFile(buf);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private synchronized void writeToFile(byte[] buf) throws IOException {
		tempFile.write(buf, 0, buf.length);
	}
	

	
	
}
