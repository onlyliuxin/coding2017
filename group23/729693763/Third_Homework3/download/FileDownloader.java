package com.zhous.download;

import com.zhous.download.api.Connection;
import com.zhous.download.api.ConnectionException;
import com.zhous.download.api.ConnectionManager;
import com.zhous.download.api.DownloadListener;

import java.io.File;
import java.util.Set;


public class FileDownloader {

	String url="https://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=detail&url=https%3A%2F%2Ftimgsa.baidu.com%2Ftimg%3Fimage%26quality%3D80%26size%3Db9999_10000%26sec%3D1490203615530%26di%3D2b1827956a01011d3634f3e04b01b15c%26imgtype%3D0%26src%3Dhttp%253A%252F%252Fimg15.3lian.com%252F2015%252Fh1%252F338%252Fd%252F157.jpg&thumburl=https%3A%2F%2Fss1.bdstatic.com%2F70cFvXSh_Q1YnxGkpoWK1HF6hhy%2Fit%2Fu%3D2613489365%2C2336631324%26fm%3D23%26gp%3D0.jpg";
	private static int threadNum = 5;
	private static int threadCount = 0;
	int avgBytes = 0;

	DownloadListener listener;
	ConnectionManager cm;
	File file = null;

	public FileDownloader(String _url,String fileName) {
		this.url = _url;
		this.file = new File(fileName);
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
		try {

			conn = cm.open(this.url);
			int length = conn.getContentLength();
			avgBytes = length / threadNum;
			//使用5个线程去下载；
			for (int i = 0; i < threadNum; i++) {
				int startPos = i*avgBytes;
				int endPos = startPos + avgBytes - 1;
				if(i == threadNum -1) {
					endPos = length;
				}

				Connection c = cm.open(this.url);
				DownloadThread thread = new DownloadThread(c,startPos,endPos,file,this.getListener());
				thread.setName("Thread"+(i+1) );
				thread.start();
			}

		} catch (ConnectionException e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.close();
			}
		}




	}



	public void setListener (DownloadListener listener){
		this.listener = listener;
	}

	public synchronized static  boolean isDownloadFinish(){
		threadCount++;
		return threadCount == threadNum;
	}



	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}

	public DownloadListener getListener(){
		return this.listener;
	}

}
