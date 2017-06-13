package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;
	private byte[] data;
	FileDownloader dl = null;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		try {
		System.out.println("Thread: " + currentThread().getName() + " starts with: " + startPos + " -> " + endPos);
		data = conn.read(startPos, endPos);
		//dl.acquireFilePermit();
		dl.acquireFilePermit();
		writeToFile(data, startPos, dl.getTargetFile());
		dl.releaseFilePermit();
		dl.acquireFinishCounterPermit();
		dl.reportDownloadFinished(data, startPos);
		dl.releaseFinishCounterPermit();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setCallBack(FileDownloader dl){
		this.dl = dl;
	}
	
//	public void writeToFile(byte[] data, int offset, File target){
//		FileOutputStream fs = null;
//		try {
//			fs = new FileOutputStream(target);
//			FileChannel fc = fs.getChannel();
//			ByteBuffer bb = ByteBuffer.allocate(data.length);
//			bb.clear();
//			bb.put(data);
//			//fc.write(src, position)
//			dl.acquireFilePermit();
//			System.out.println("write to file: " + target.getAbsolutePath());
//			System.out.println("starting at: " + offset + " length: " + data.length);
//			//fs.write(data, 0, data.length);
//			dl.releaseFilePermit();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			if(fs != null)
//				try {
//					fs.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		}
//		
//	}
	public static void writeToFile(byte[] data, int pos, RandomAccessFile targetFile){
		try{
		  targetFile.seek(pos);
		  targetFile.write(data, 0, data.length);
	      System.out.println(Thread.currentThread().getName() + " start position = " + pos +", end position = " + (pos + data.length -1));
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
}
