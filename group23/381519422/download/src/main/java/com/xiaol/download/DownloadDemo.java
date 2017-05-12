package com.xiaol.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 下载DEMO
 * @author ShawnLin
 */
public class DownloadDemo {

	public void download(String url) {
		URL u;
		try {
			u = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) u.openConnection();
			int contentLength = httpURLConnection.getContentLength();
			httpURLConnection.connect();
			InputStream inputStream = httpURLConnection.getInputStream();
			File file = new File(getFileName(url));
			RandomAccessFile raf = new RandomAccessFile(file, "rwd");
			byte[] read = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(read)) != -1) {
				raf.write(read, 0, length);
			}
			inputStream.close();
			raf.close();
			System.out.println("下载完毕，位置[" + file.getAbsolutePath() + "]");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getFileName(String path) {
		int index = path.lastIndexOf("/");
		return path.substring(index + 1);
	}

	public static void main(String[] args) {
		DownloadDemo dd = new DownloadDemo();
		String url = "http://ww3.sinaimg.cn/large/729119fdgw1f0nnftw3hij21kw11x4qp.jpg";
		dd.download(url);
	}
}
