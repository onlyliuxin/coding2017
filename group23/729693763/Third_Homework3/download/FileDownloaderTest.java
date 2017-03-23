package com.zhous.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zhous.download.api.ConnectionManager;
import com.zhous.download.api.DownloadListener;
import com.zhous.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	boolean downloadFinished = false;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {

		String url="http://www.mydeskcity.com//upload/pc/112/960x600/1351650816239.jpg";
		String github = "https://github.com/em14Vito/coding2017/archive/master.zip";

//		FileDownloader downloader = new FileDownloader(url,"Data23.jpg");
		FileDownloader downloader = new FileDownloader(github,"github.zip");
	
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
				//休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！");
		
		

	}

}
