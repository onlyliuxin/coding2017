package week3.download;

import java.net.URL;
import java.util.concurrent.CyclicBarrier;

import week3.download.api.Connection;
import week3.download.api.ConnectionException;
import week3.download.api.ConnectionManager;
import week3.download.api.DownloadListener;
import week3.download.api.impl.ConnectionManagerImpl;

public class FileDownloader {

	private String url;
	private String localFile;
	private static final int DOWNLOAD_THREAD_NUM = 6;

	private DownloadListener listener;

	public FileDownloader(String url, String localFile) {
		this.url = url;
		this.localFile = localFile;
	}

	public void execute() {
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos,
		// endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		// 线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接，
		// 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载， 注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法

		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。

		CyclicBarrier barrier = new CyclicBarrier(DOWNLOAD_THREAD_NUM,
				new Runnable() {
					@Override
					public void run() {
						listener.notifyFinished();
					}
				});

		ConnectionManager connManager = new ConnectionManagerImpl();

		Connection conn = null;

		try {
			conn = connManager.open(url);
			int totalLen = conn.getContentLength();

			int[][] range=allocateDownloadRange(DOWNLOAD_THREAD_NUM,totalLen);
			
			for (int i = 0; i < DOWNLOAD_THREAD_NUM; i++) {
				
				DownloadThread thread = new DownloadThread(connManager.open(url), range[i][0],
						range[i][1], localFile, barrier);
				
				thread.start();
			}

		} catch (ConnectionException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	private int[][] allocateDownloadRange(int threadNum,int totalLen){
		
		int[][] range=new int[threadNum][2];
		
		int eachThreadSize=totalLen/threadNum;
		
	    int left=totalLen%threadNum;//剩余的由最后一个线程处理
	    
	    for(int i=0;i<threadNum;i++){
	    	int startPos=i*eachThreadSize;
	    	int endPos=(i+1)*eachThreadSize-1;
	    	if(i == threadNum){
	    		endPos+=left;
	    	}
	    	range[i][0]=startPos;
	    	range[i][1]=endPos;
	    }
	    	
		return range;
	}
	
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public DownloadListener getListener() {
		return listener;
	}

}