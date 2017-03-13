package com.louis.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.louis.download.LYFileDownloader;
import com.louis.download.api.LYDownloadListener;
import com.louis.download.impl.LYConnectionManagerImpl;

public class LYFileDownloaderTest {
	boolean downloadFinished = false;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {
		
		String url = "http://avatar.csdn.net/9/B/0/1_xb12369.jpg";
		new LYFileDownloader(url, new LYConnectionManagerImpl(),  new LYDownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}
		}).execute();
		
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
