package download;

import java.io.FileOutputStream;

import download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	byte [] buff;
	FileOutputStream file;

	public DownloadThread( Connection conn, int startPos, int endPos, byte[] buff ,FileOutputStream file){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.file = file;
		this.buff = buff;
	}
	public void run(){	
		
	}
}
