package code03;

import code03.api.Connection;
import code03.api.ConnectionException;
import code03.api.ConnectionManager;
import code03.api.DownloadListener;
import code03.impl.ConnectionManagerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程启动类
 */

public class FileDownloader {
	
	private String url;
	private DownloadListener listener;
	private ConnectionManager cm;
	private static boolean downloadFinished = false;

	private final static int THREAD_NUM = 5;

	public FileDownloader(String _url) {
		this.url = _url;
	}

	/*
	(1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
	(2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
	     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。*/
	public void execute(){
		Connection conn = null;
		try {
			//启动线程
			int startPos = 0, endPos = 0;
			List<Thread> threads = new ArrayList<Thread>();
			for (int i = 0; i < THREAD_NUM; i++) {
				conn = cm.open(this.url); //每次都要重新获取一个connection.imputstream
				int length = conn.getContentLength();
				startPos = length/THREAD_NUM *  i;
				endPos = length/THREAD_NUM * (i + 1)- 1;
				DownloadThread downloadThread = new DownloadThread(conn,startPos,endPos);
				threads.add(downloadThread);
				downloadThread.start();
			}

			//调用join方法，确保所有线程的工作已经完成
			for (int i = 0; i < THREAD_NUM; i++) {
				try {
					threads.get(i).join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			listener.notifyFinished();
		} catch (ConnectionException e) {
			e.printStackTrace();
		} finally {
			if(conn != null){
				conn.close();
			}
		}
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


	public static void main(String[] args) {

		String url = "http://litten.me/assets/blogImg/litten.png";
		FileDownloader fileDownloader = new FileDownloader(url);
		ConnectionManager cm = new ConnectionManagerImpl();
		fileDownloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}
		});
		fileDownloader.setConnectionManager(cm);
		fileDownloader.execute();


		while (!downloadFinished){
			try {
				System.out.println("还没有下载完成，休眠五秒");
				//休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("download finished ! ");


	}
	
}
