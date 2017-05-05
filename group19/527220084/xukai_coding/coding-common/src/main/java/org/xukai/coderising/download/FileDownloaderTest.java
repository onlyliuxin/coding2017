package org.xukai.coderising.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xukai.coderising.download.api.ConnectionException;
import org.xukai.coderising.download.api.ConnectionManager;
import org.xukai.coderising.download.api.DownloadListener;
import org.xukai.coderising.download.impl.ConnectionManagerImpl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileDownloaderTest {

	boolean downloadFinished = false;

	String path = "";

	@Before
	public void setUp() throws Exception {
		path = System.getProperty("user.dir");
		System.out.println(path+"image");
		System.out.println(System.getProperty("user.dir"));

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {

		String url = "http://img1.mm131.com/pic/2723/4.jpg";

		FileDownloader downloader = new FileDownloader(url);


		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);

		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
				File file = new File(path + "/image/阳光暖妹.jpg");
				System.out.println(file.length());
			}

		});


		try {
			downloader.execute();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}

		// 等待多线程下载程序执行完毕
		while (!downloadFinished) {
			try {
				System.out.println("还没有下载完成，休眠五秒");
				//休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！");



	}
	@Test
	public void testDownload2() throws IOException {

		String urlStr = "http://img1.mm131.com/pic/2723/4.jpg";

		URL url = new URL(urlStr);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod("GET");
		urlConnection.setDoOutput(false);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);


		urlConnection.connect();
		int length = urlConnection.getContentLength();
		System.out.println(length);
		BufferedInputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
		byte[] buff = new byte[length];
		int len = 0;
		FileOutputStream outputStream = new FileOutputStream(path+"美女2.jpg");
		FileChannel channel = outputStream.getChannel();
		int hasRead = 0;
		while (hasRead < length &&(len=inputStream.read(buff,hasRead,buff.length-hasRead)) != -1 ){

			hasRead = hasRead + len;
			if ((hasRead -length) > 0) {
				len = len - (hasRead-length/2);
			}
			System.out.println("hasRead:" + hasRead);
		}
		System.out.println(buff[buff.length/2]);
		ByteBuffer wrap = ByteBuffer.wrap(buff, 0, hasRead);
		channel.write(wrap);
		inputStream.close();
		outputStream.close();
		channel.close();

	}

	@Test
	public void compare() throws IOException {
		FileInputStream in2 = new FileInputStream(path+"/image/阳光暖妹.jpg");
		FileInputStream in1 = new FileInputStream(path+"/image/阳光暖妹2.jpg");
		byte[] byte1 = new byte[1];
		byte[] byte2 = new byte[1];
		int len1 = 0;
		int len2 = 0;
		for (int i = 0; i < 80000; i++) {
			len1 = in1.read(byte1);
			len2 = in2.read(byte2);
//			if (i >43825 && i < 43830) {
//				System.out.println(byte1[0] + ":" + byte2[0]);
//			}
			if (byte1[0] != byte2[0]) {
				System.out.print(i + " ");
			}
		}

	}

}
