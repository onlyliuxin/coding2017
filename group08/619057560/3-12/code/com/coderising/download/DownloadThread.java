package com.coderising.download;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread{

	private static final String FILENAME = "test.jpg";
	
	Connection conn;
	int startPos;
	int endPos;
	
	DownloadThreadListener listener;
	int tag = 0;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	public void setTagAndListener(int tag, DownloadThreadListener listener) {
		this.tag = tag;
		this.listener = listener;
	}
	
	public void run(){
		RandomAccessFile raf = null;
		int offset = startPos, len;
		byte[] buffer;
		try {
			raf = new RandomAccessFile(FILENAME, "rw");
			if (raf.length() == 0) {
				raf.setLength(conn.getContentLength());
			}
			raf.seek(startPos);
			do {
				len = endPos + 1 - offset;
				if (len > 10000) {
					len = 10000;
				}
				buffer = conn.read(offset, offset + len - 1);
				if (buffer == null) {
					processException(new NullPointerException());
					return;
				}
				raf.write(buffer);
				offset += len;
			} while (offset < endPos);
			if (listener != null) {
				listener.onFinished(conn, tag, true);
			}
		} catch (FileNotFoundException e) {
			processException(e);
		} catch (IOException e) {
			processException(e);
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void processException(Exception e) {
		e.printStackTrace();
		if (listener != null) {
			listener.onFinished(conn, tag, false);
		}
	}
	
	public static interface DownloadThreadListener {
		public void onFinished(Connection conn, int tag, boolean succeeded);
	}
}
