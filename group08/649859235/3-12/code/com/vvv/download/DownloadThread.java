package com.vvv.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.vvv.download.api.Connection;

public class DownloadThread extends Thread{
	private int startPos;
	private int endPos;
    private boolean isDownloadEnd;
    private String threadName;
    private Connection conn;
    private FileDownloader fileDownloader;
    
	public DownloadThread(Connection conn, int startPos, int endPos, String tName, FileDownloader fileDownloader){
		this.startPos = startPos;
		this.endPos = endPos;
		this.threadName = tName;
		this.setName(tName);
        this.conn = conn;
        this.fileDownloader = fileDownloader;
	}
	
	 public boolean isDownloadEnd() {
	        return isDownloadEnd;
    }
	 
	public void run() {
		try {
			byte[] data = conn.read(startPos, endPos);
			conn.close();
			System.out.println(threadName + " read length "+data.length +  ", startPos: "+ startPos + ", endPos: " + endPos);
			int writelen = write(data, startPos, data.length);
			isDownloadEnd = true;
			System.out.println("===========" + threadName+" .... write  end ... " + writelen);
			fileDownloader.addDownNum();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized int write(byte[] buff, int start, int length) {
		int i = -1;
		RandomAccessFile raf = null;
			try {
				raf = new RandomAccessFile(fileDownloader.getFileName(), "rw");
				System.out.println(threadName + " write length "+buff.length+ ", start: " + start+" write file "+fileDownloader.getFileName());
				raf.seek(start);
				raf.write(buff, 0, buff.length);
				i = buff.length;
			} catch (IOException e) {
				e.printStackTrace();
			}finally {  
				 try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
			}
		return i;
	}
	
}
