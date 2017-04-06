package com.coding.basic.homework_03.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.coding.basic.homework_03.download.api.Connection;
import com.coding.basic.homework_03.download.api.ConnectionManager;
import com.coding.basic.homework_03.download.api.DownloadListener;

public class FileDownloader {

	String url;

	DownloadListener listener;

	ConnectionManager cm;
	private static final long KBSIZE = 1024;
	private static final long MBSIZE = 1024 * 1024;

	public FileDownloader(String _url) {
		this.url = _url;

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
		Connection conn = null;
		try {
			conn = cm.open(this.url);
			int length = conn.getContentLength();
			createFile(new File("copy.jpg"), length);
//			System.out.println(length);
			int len = length / 5;
			DownloadThread t1 = new DownloadThread(cm.open(this.url), 0, len - 1);
			DownloadThread t2 = new DownloadThread(cm.open(this.url), len, len * 2 - 1);
			DownloadThread t3 = new DownloadThread(cm.open(this.url), len * 2, len * 3 - 1);
			DownloadThread t4 = new DownloadThread(cm.open(this.url), len * 3, len * 4 - 1);
			DownloadThread t5 = new DownloadThread(cm.open(this.url), len * 4, len * 5 - 1);
			DownloadThread t6 = new DownloadThread(cm.open(this.url), len * 5, length - 1);
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			t5.start();
			t6.start();

			while (!(t1.downOver && t2.downOver && t3.downOver && t4.downOver && t5.downOver && t6.downOver)) {
				try {
					System.out.println("还有线程没有下载完成，等待五秒...");
					// 休眠5秒
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	/**
	 * 创建一个临时文件
	 * 
	 * @param file
	 * @param length
	 * @throws IOException
	 */
	private void createFile(File file, int length) throws IOException {
		long batchSize = 0;
		FileOutputStream fos = null;
		if (!file.exists()) {
			file.createNewFile();
		}

		if (length > KBSIZE) {
			batchSize = KBSIZE;
		}
		if (length > MBSIZE) {
			batchSize = MBSIZE;
		}
		fos = new FileOutputStream(file);
		int times = length / (int) batchSize;
		int last = length % (int) batchSize;
		FileChannel channel = fos.getChannel();
		for (int i = 0; i < times; i++) {
			ByteBuffer buffer = ByteBuffer.allocate((int) batchSize);
			channel.write(buffer);
		}
		ByteBuffer buffer = ByteBuffer.allocate(last);
		channel.write(buffer);
		fos.close();

	}

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
