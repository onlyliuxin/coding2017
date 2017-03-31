import api.Connection;
import api.ConnectionManager;
import api.DownloadListener;

import java.io.FileOutputStream;
import java.util.concurrent.CountDownLatch;


public class FileDownloader {
	
	String url;
	
	DownloadListener downloadListener;
	
	ConnectionManager connectionManager;
	

	public FileDownloader(String _url) {
		this.url = _url;
		
	}
	int threadNum=3;
	public void execute(){//TODO 主体
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
		Connection connection = null;
		try {
			connection = connectionManager.open(this.url);
			int totalLength = connection.getContentLength();

			//计算出每一块的大小
			int perLength =totalLength /threadNum +1;

			CountDownLatch threadSignal = new CountDownLatch(threadNum);//初始化countDown
			for (int i = 0; i < threadNum; i++) {//开threadNum个线程
				int length = perLength;
				//如果是最后一块, 则使用总数来减去前面块的总和
				if (i == (threadNum - 1)) {
					length = totalLength- i * perLength;
				}
				connection = connectionManager.open(this.url);
				new DownloadThread(connection,i * perLength,i* perLength+length-1,threadSignal).start();

			}
			FileOutputStream fos = new FileOutputStream("D:\\new.jpg");

			threadSignal.await();//等待所有子线程执行完
			for(Integer i:DownloadThread.partMap.keySet()){
				fos.write(DownloadThread.partMap.get(i));
			}
			fos.close();
			getDownloadListener().notifyFinished();
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		
		
		
	}
	
	public void setDownloadListener(DownloadListener downloadListener) {
		this.downloadListener = downloadListener;
	}

	
	
	public void setConnectionManager(ConnectionManager ucm){
		this.connectionManager = ucm;
	}
	
	public DownloadListener getDownloadListener(){
		return this.downloadListener;
	}
	
}
