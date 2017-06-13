package org.wsc.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.wsc.coderising.download.api.Connection;
import org.wsc.coderising.download.api.ConnectionException;
import org.wsc.coderising.download.api.DownloadListener;

/**
 * 下载线程
 *
 * @author Administrator
 * @date 2017年3月6日下午7:03:41
 * @version v1.0
 *
 */
public class DownloadThread extends Thread{

	private RandomAccessFile accessFile;
	/** 连接 */
	private Connection conn;
	/** 开始处 */
	private int startPos;
	/** 结束处 */
	private int endPos;
	/** 回调函数 */
	private DownloadListener listener;
	
	public DownloadThread( Connection conn, int startPos, int endPos,DownloadListener listener){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.listener = listener;
	}
	public void run(){
		try {
			byte[] bt = conn.read(startPos, endPos);
			accessFile = new RandomAccessFile("./"+conn.getFileName(), "rw");
			accessFile.seek(startPos);
			accessFile.write(bt);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}finally {
			if(accessFile != null){
				try {
					accessFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null)
				conn.close();
			if(listener!=null)
				listener.notifyFinished();
		}
		
	}
}
