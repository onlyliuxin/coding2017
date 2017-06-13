package com.github.eloiseSJTU.coding2017.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.github.eloiseSJTU.coding2017.download.api.Connection;
import com.github.eloiseSJTU.coding2017.download.api.ConnectionException;
import com.github.eloiseSJTU.coding2017.download.api.ConnectionManager;
import com.github.eloiseSJTU.coding2017.download.api.DownloadListener;

public class FileDownloader {

	String url;
	DownloadListener listener;
	ConnectionManager cm;
	int threadNum = 3;

	public FileDownloader(String url) {
		this.url = url;
	}

	public void execute() {
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager,
		// 可以打开一个连接，通过Connection可以读取其中的一段（用startPos,endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		// 线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接，
		// 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载， 注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法

		System.out.println("主线程开始");
		Connection conn = null;
		try {
			if ((conn = cm.open(url)) != null) {
				int length = conn.getContentLength();
				System.out.println("长度：" + length);
				if (length != -1) {
					String fileName = url.substring(url.lastIndexOf("/") + 1);
					RandomAccessFile file = new RandomAccessFile(new File(fileName), "rwd");
					file.setLength(length);
					file.close();
					if (length < threadNum) {
						threadNum = 1;
					}
					Connection[] connections = new Connection[threadNum];
					DownloadThread[] threads = new DownloadThread[threadNum];
					int step = Math.floorDiv(length + threadNum - 1, threadNum);
					for (int i = 0; i < threadNum; i++) {
						connections[i] = cm.open(url);
						threads[i] = new DownloadThread(connections[i], i * step,
								Math.min((i + 1) * step - 1, length - 1), fileName);
						threads[i].start();
					}
					new Thread(new Runnable() {

						@Override
						public void run() {
							System.out.println("监控线程开始");
							try {
								for (int i = 0; i < threadNum; i++) {
									threads[i].join();
								}
								if (listener != null) {
									listener.notifyFinished();
								}
							} catch (InterruptedException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							} finally {
								for (int i = 0; i < threadNum; i++) {
									if (connections[i] != null) {
										connections[i].close();
									}
								}
								System.out.println("监控线程结束");
							}
						}
					}).start();
				}
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (FileNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
			System.out.println("主线程结束");
		}
	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager cm) {
		this.cm = cm;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

}
