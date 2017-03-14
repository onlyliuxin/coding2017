package com.louis.download;

import com.louis.download.api.LYConnectionCallBack;
import com.louis.download.api.LYConnection;

public class LYDownloadThread extends Thread {
	LYConnection conn;
	int startPos;
	int endPos;
	LYConnectionCallBack callBack;

	public LYDownloadThread( LYConnection conn, int startPos, int endPos, LYConnectionCallBack callBack){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.callBack = callBack;
	}
	
	public void run() {	
		try{
			System.out.println("begin download: startPos="+startPos+",endPos="+endPos);
			byte[] buffer = conn.read(startPos , endPos);
			this.callBack.excute(buffer);
			System.out.println("finish download: startPos="+startPos+",endPos="+endPos);
		}catch(Exception e){
			System.out.println("download error:startPos="+startPos+",endPos="+endPos+",msg:"+e.getMessage());
		}
	}
}
