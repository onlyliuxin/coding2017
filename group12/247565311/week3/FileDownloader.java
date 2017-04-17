package week3;
import java.util.concurrent.CyclicBarrier;
import java.io.IOException;
import java.io.RandomAccessFile;

import week3.api.Connection;
import week3.api.ConnectionException;
import week3.api.ConnectionManager;
import week3.api.DownloadListener;
import week3.impl.ConnectionManagerImpl;

public class FileDownloader {
	private int MaxThreadNum = 4;
	private String url = null,path=null;
	DownloadListener listener = null;
	private ConnectionManager cm = new ConnectionManagerImpl();
	
	public FileDownloader(String weburl,String localpath) {
		this.url = weburl;
		this.path = localpath;
	}
	
	public void execute() throws InterruptedException{
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
		CyclicBarrier barr= new CyclicBarrier(MaxThreadNum,new Runnable(){
			public void run(){
				listener.notifyFinished();
			}
		});
		try {
			conn = cm.open(this.url);
			int length = conn.getContentLength();	
			// 在这里创建出一个同样大小的空文件， 路径是path
			RandomAccessFile tarfile = new RandomAccessFile(path,"rw");
			tarfile.setLength(length);
			tarfile.close();
			Thread[] threads = new Thread[4];
			threads[0] = new DownloadThread(barr,cm.open(this.url),0,length/4,path);
			threads[1] = new DownloadThread(barr,cm.open(this.url),length/4,length/2,path);
		    threads[2] = new DownloadThread(barr,cm.open(this.url),length/2,3*length/4,path);
		    threads[3] = new DownloadThread(barr,cm.open(this.url),3*length/4,length,path);
			for(int i=0;i<4;i++)
			    threads[i].start();
		} catch (ConnectionException | IOException e) {
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
	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
	public DownloadListener getListener(){
		return this.listener;
	}
	public double getDownPercent(){
		return 0.0;
	}
}
