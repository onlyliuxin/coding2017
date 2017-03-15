package com.coderising.download;

import java.io.File;
import java.util.concurrent.CyclicBarrier;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;


public class FileDownloader {

	boolean isFinished = false;

	String url;

	DownloadListener listener;

	ConnectionManager cm;

	private static final int THREAD_NUM = 3;

	public FileDownloader(String _url) {
		this.url = _url;
	}

	public void execute(){

		CyclicBarrier barrier = new CyclicBarrier(THREAD_NUM, new Runnable() {

			@Override
			public void run() {
				listener.notifyFinished();
			}
		});
		Connection conn = null;
		try {

			conn = cm.open(this.url);

			int length = conn.getContentLength();
<<<<<<< HEAD
			System.out.println("length:"+length);
			String filePath = getFileName(this.url);
=======
			String filePath =getFileName(this.url);
			//System.out.println(filePath);
			
>>>>>>> 3b68226a35b37a691f64bcd4a1e219efba00a479
			File file = new File(filePath);
			
			int blockSize = (length % THREAD_NUM == 0 ) ? length / THREAD_NUM : (length / THREAD_NUM + 1);
			for (int i = 0; i < THREAD_NUM; i++) {
				int startPos = i * blockSize;
				int endPos = (i+1) * blockSize - 1;
				if(endPos > length - 1){
					endPos = length - 1;
				}
<<<<<<< HEAD
				new DownloadThread(barrier , conn, startPos, endPos , file).start();

=======
				new DownloadThread(barrier , conn, startPos, endPos , file).start();	
>>>>>>> 3b68226a35b37a691f64bcd4a1e219efba00a479
			}

		} catch (ConnectionException e) {			
			System.out.println(e.getMessage());
		} finally{
			if(conn != null){
				conn.close();
			}
		}
	}



<<<<<<< HEAD
=======

	}

>>>>>>> 3b68226a35b37a691f64bcd4a1e219efba00a479
	private String getFileName(String url) {
		int index = url.lastIndexOf("/");
		return url.substring(index + 1 , url.length());
	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}

	public DownloadListener getListener(){
		return this.listener;
	}
}
