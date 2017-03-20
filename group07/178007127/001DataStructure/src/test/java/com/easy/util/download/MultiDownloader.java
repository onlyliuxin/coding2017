package com.easy.util.download;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultiDownloader {
	public static final int THREAD_COUNT = 2;

	public static void main(String[] args) throws Exception {
		String path = "http://om9xacvdp.bkt.clouddn.com/blog-self-logo.jpg?a=1";
		// 连接服务器，获取一个文件，获取文件的长度，在本地创建一个大小跟服务器文件大小一样的临时文件
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置网络请求超时时间
		conn.setConnectTimeout(5000);
		// 设置请求方式
		conn.setRequestMethod("GET");
		int code = conn.getResponseCode();
		if (code == 200) {
			// 服务器返回的数据的长度，实际就是文件的长度
			int length = conn.getContentLength();
			System.out.println("----文件总长度----" + length);
			// 在客户端本地创建出来一个大小跟服务器端文件一样大小的临时文件
			RandomAccessFile raf = new RandomAccessFile("d://github666.jpg", "rwd");
			// 指定创建的这个文件的长度
			raf.setLength(length);
			// 关闭raf
			raf.close();
			// 假设是3个线程去下载资源
			// 平均每一个线程下载的文件的大小
			int blockSize = length / THREAD_COUNT;
			for (int threadId = 1; threadId <= THREAD_COUNT; threadId++) {
				// 计算每个线程下载的开始位置和结束位置
				int startIndex = (threadId - 1) * blockSize;
				int endIndex = threadId * blockSize - 1;
				if (threadId == THREAD_COUNT) {
					endIndex = length;
				}
				System.out.println(
						"----threadId---" + threadId + "--startIndex--" + startIndex + "--endIndex--" + endIndex);
				// 开启每一个线程
				new DownloadThread(path, threadId, startIndex, endIndex).start();
			}
		}
	}
}
