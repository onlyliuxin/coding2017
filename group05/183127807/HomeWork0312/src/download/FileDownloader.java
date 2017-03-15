package download;

import download.api.Connection;
import download.api.ConnectionException;
import download.api.DownloadListener;
import download.impl.ConnectionManagerImpl;

import java.io.RandomAccessFile;


public class FileDownloader {
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManagerImpl cm;
	

	public FileDownloader(String _url) {
		this.url = _url;
		
	}
	
	public void execute(){
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		//     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
		
		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
		Connection conn = null;
		RandomAccessFile raf = null;
		try {
			int threadCount=3;
			int length = cm.getContentLength(url);

			for (int i=0;i<threadCount;i++) {
				int tLength = length / threadCount;
				int startPos = tLength*i;
				int endPos;
				if (i != tLength - 1) {
					endPos = tLength * (i - 1) + 1;
				} else {
					endPos = length - 1;
				}

				new DownloadThread(threadCount - 1, url, startPos, endPos, null, new DownloadListener() {
					@Override
					public void notifyFinished() {
						if (FileDownloader.this.listener != null) {
							FileDownloader.this.listener.notifyFinished();
						}
					}
				}).start();

			}



			//new DownloadThread(conn,0,length-1).start();
			
		} catch (ConnectionException e) {			
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		
		
		
		
	}
	
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	
	
	public void setConnectionManager(ConnectionManagerImpl ucm){
		this.cm = ucm;
	}
	
	public DownloadListener getListener(){
		return this.listener;
	}
	
}
