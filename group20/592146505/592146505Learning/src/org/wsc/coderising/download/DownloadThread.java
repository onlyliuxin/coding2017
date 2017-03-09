package org.wsc.coderising.download;

import org.wsc.coderising.download.api.Connection;

/**
 * 下载进程
 *
 * @author Administrator
 * @date 2017年3月6日下午7:03:41
 * @version v1.0
 *
 */
public class DownloadThread extends Thread{

	/** 连接 */
	Connection conn;
	/** 开始处 */
	int startPos;
	/** 结束处 */
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		
	}
}
