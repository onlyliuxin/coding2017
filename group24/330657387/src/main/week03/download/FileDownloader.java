package main.week03.download;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import main.week03.download.api.Connection;
import main.week03.download.api.ConnectionException;
import main.week03.download.api.ConnectionManager;
import main.week03.download.api.DownloadListener;

public class FileDownloader {

	private String url;
	private String filePath = "/";

	public DownloadListener listener;

	public ConnectionManager cm;

	private int DOWNLOAD_TRHEAD_NUM = 3;

	public FileDownloader(String _url, String filePath) {
		this.url = _url;
		this.filePath = filePath;
	}

	public void execute() throws IOException {
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

		//当支线线程全部结束，即每个线程都调用了await方法，就会触发主线程，即listener的notify
		CyclicBarrier barrier = new CyclicBarrier(DOWNLOAD_TRHEAD_NUM,
				new Runnable() {
					public void run() {
						listener.notifyFinished();
					}
				});

		Connection conn = null;
		try {

			conn = cm.open(this.url);

			int length = conn.getContentLength();

			//确保文件里有足够的空间，就先创建空文件。
			createPlaceHolderFile(this.filePath, length);

			//每个线程的读取区间
			int[][] ranges = allocateDownloadRange(DOWNLOAD_TRHEAD_NUM, length);

			for (int i = 0; i < DOWNLOAD_TRHEAD_NUM; i++) {

				DownloadThread thread = new DownloadThread(cm.open(url),
						ranges[i][0], ranges[i][1], filePath, barrier);

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

	private int[][] allocateDownloadRange(int threadNum, int contentLen) {
		int[][] ranges = new int[threadNum][2];

		int eachThreadSize = contentLen / threadNum;// 每个线程需要下载的文件大小
		int left = contentLen % threadNum;// 剩下的归最后一个线程来处理

		for (int i = 0; i < threadNum; i++) {

			int startPos = i * eachThreadSize;

			int endPos = (i + 1) * eachThreadSize - 1;

			if ((i == (threadNum - 1))) {
				endPos += left;
			}
			ranges[i][0] = startPos;
			ranges[i][1] = endPos;

		}

		return ranges;
	}

	private void createPlaceHolderFile(String filePath, int contentLen)
			throws IOException {
		RandomAccessFile file = new RandomAccessFile(filePath, "rw");

		for (int i = 0; i < contentLen; i++) {
			file.write(1);
		}

		file.close();
	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager cm) {
		this.cm = cm;
	}

	public DownloadListener getListener() {
		return this.listener;
	}

}
