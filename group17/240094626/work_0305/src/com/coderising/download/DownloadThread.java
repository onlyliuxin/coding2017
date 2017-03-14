package com.coderising.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	int threadId;
	private static final int  BUFF_LENGTH = 1024;
	private final static String TEMP_FILE_PATH = "E:/temp";
	String filePath ;
	boolean isFinished = false;
	long currSize = 0;

	public DownloadThread( Connection conn, int startPos, int endPos,int threadId){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.threadId = threadId;
		filePath = TEMP_FILE_PATH + "/"+threadId+".tmp";
	}
	public void run(){	
		try {
			// create  temp file with threadId
			createTmepFile();
			
			// read  temp file 
			writeTempFile();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void createTmepFile() {
		//判断路径temp是否存在，不存在则先创建
    	File tempDir = new File(TEMP_FILE_PATH);
    	if(!tempDir.exists() || !tempDir.isDirectory()){
    		tempDir.mkdir();
    	}
    	
    	File file = new File(filePath);
    	if(file.exists()){
			currSize = file.length();
		}else{
			currSize = 0;
		}
	}
	private int writeTempFile() {
		int size = 0;
    	OutputStream fout = null;
    	try{
    	    fout = new FileOutputStream(filePath, true);	
    	    for(int i = 0; i < sbLogMsg.size(); i++){
                StringBuffer logMsg = sbLogMsg.get(i);
                byte[] tmpBytes = CommUtil.StringToBytes(logMsg.toString());
                fout.write(tmpBytes);
                size += tmpBytes.length;
             }
    	}catch(Exception e){
    	    e.printStackTrace();
    	}finally{
    		if(fout != null){
    			fout.close();
    		}
    	}
    	return size;
		
	}
}
