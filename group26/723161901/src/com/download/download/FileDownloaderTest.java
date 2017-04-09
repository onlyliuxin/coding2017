package com;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.api.ConnectionManager;
import com.api.DownloadListener;
import com.impl.ConnectionManagerImpl;

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
		
		String url = "http://images2015.cnblogs.com/news/24442/201703/24442-20170331150421570-489464769.jpg";
		
		FileDownloader downloader = new FileDownloader(url, "/Users/Macx/Workspaces/MyEclipse 2017 CI/download/src/489464769.jpg");

	
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
