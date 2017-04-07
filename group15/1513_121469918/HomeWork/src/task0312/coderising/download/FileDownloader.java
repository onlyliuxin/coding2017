package task0312.coderising.download;

import java.io.IOException;
import java.io.RandomAccessFile;

import task0312.coderising.download.api.Connection;
import task0312.coderising.download.api.ConnectionManager;
import task0312.coderising.download.api.DownloadListener;
import task0312.coderising.download.impl.ConnectionManagerImpl;


public class FileDownloader {
	Thread[] threadList = new Thread[6];
	String url;
	DownloadListener listener;
	ConnectionManager cm;

	public FileDownloader(String _url) {
		this.url = _url;
	}

	public void execute() {
		RandomAccessFile raf = null;
		try {
			// 打开连接获取长度
			ConnectionManagerImpl cm = new ConnectionManagerImpl();
			Connection conn = cm.open(url);
			int length = conn.getContentLength();

			// 创建本地接收文件
			String localPath = url.substring(url.lastIndexOf('/') + 1);
			raf = new RandomAccessFile(localPath, "rwd");
			raf.setLength(length);
			raf.close();

			int blockSize = length / threadList.length;// 每个线程下载的大小
			for (int threadNum = 0; threadNum < threadList.length; threadNum++) {
				// 定义每个线程开始位置
				int threadStart = threadNum * blockSize;
				// 定义每个线程结束位置
				int threadEnd = (threadNum + 1) * blockSize - 1;
				// 定义最后线程结束位置为总长度-1
				if (threadNum == threadList.length - 1) {
					threadEnd = length - 1;
				}
				String threadID = "Thread-" + (threadNum + 1);
				threadList[threadNum] = new DownloadThread(url, localPath, threadStart, threadEnd, listener);
				threadList[threadNum].start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
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

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm) {
		this.cm = ucm;
	}

	public DownloadListener getListener() {
		return this.listener;
	}

}
