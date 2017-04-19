package com.coderising.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	boolean downloadFinished = false;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static String qq = "http://sw.bos.baidu.com/sw-search-sp/software/89179b0b248b1/QQ_8.9.20026.0_setup.exe";
	public static String picture = "http://image.beekka.com/blog/201304/bg2013042401.jpg";
	public static String foxmail = "http://sw.bos.baidu.com/sw-search-sp/software/6c7bb8b6674d0/fm728chb379_7.2.8.379_setup.exe";

	@Test
	public void testDownload() {

		String url = "http://localhost:8080/test.jpg";

		FileDownloader downloader = new FileDownloader(foxmail);

		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);

		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}

		});

		downloader.execute();

		// 等待多线程下载程序执行完毕
		while (!downloadFinished) {
			try {
				System.out.println("还没有下载完成，休眠五秒");
				// 休眠5秒
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！");

	}

}
