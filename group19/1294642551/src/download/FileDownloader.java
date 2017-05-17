package download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import download.api.Connection;
import download.api.ConnectionException;
import download.api.ConnectionManager;
import download.api.DownloadListener;


public class FileDownloader {
	
	private String url;
	private String localFile;
	private static final int DOWNLOAD_THREAD_NUM = 3;//下载线程的个数

	DownloadListener listener;
	ConnectionManager cm;//管理Connection
	
	public FileDownloader(String _url, String localFile) {
		this.url = _url;
		this.localFile = localFile;
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
		
		// 栅栏类：第一个参数表示线程的个数；
		// 		     第二个参数表示等所有的线程执行完成之后，再调用的线程
		CyclicBarrier barrier = new CyclicBarrier(DOWNLOAD_THREAD_NUM, new Runnable(){

			@Override
			public void run() {
				listener.notifyFinished();
			}});
		
		Connection conn = null;
		try {
			
			conn = cm.open(this.url);
			int length = conn.getContentLength();	
			createPlaceHolderFile(localFile, length);
			int[][] ranges = allocateDownloadRange(DOWNLOAD_THREAD_NUM, length);
			for(int i = 0; i < DOWNLOAD_THREAD_NUM; i++){
				int startPos = ranges[i][0];
				int endPos = ranges[i][1];
				DownloadThread thread = new DownloadThread(cm.open(url), startPos, endPos, localFile, barrier);
				thread.start();
			}
			
		} catch (ConnectionException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		
	}
	
	// 在下载之前，根据文件的额大小，先在硬盘中占据一个地方，以免空间不够用
	private void createPlaceHolderFile(String fileName, int contentLen) throws IOException{
		// 随机读写文件的类
		RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
		for(int i = 0; i < contentLen; i++){
			raf.write(i);
		}
		raf.close();
		
	}
	
	private int[][] allocateDownloadRange(int threadNum, int contentLen){
		int[][] ranges = new int[threadNum][2];
		int eachThreadSize = contentLen / threadNum;
		int left = contentLen % threadNum;
		for(int i = 0; i < threadNum; i++){
			int startPos = i * eachThreadSize;
			int endPos = (i+1) * eachThreadSize - 1;
			
			if(i == (threadNum - 1)){
				endPos += left;
			}
			
			ranges[i][0] = startPos;
			ranges[i][1] = endPos;
		}
		return ranges;
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
