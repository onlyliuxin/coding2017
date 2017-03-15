package org.coding.three.download;

import org.coding.three.download.api.ConnectionManager;
import org.coding.three.download.api.DownloadListener;
import org.coding.three.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


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
		
//		String url = "http://7xq43s.com1.z0.glb.clouddn.com/yunanding-6.jpg";
//		String url = "http://www.yinwang.org/blog-cn/2016/11/17/all-about-hillary";
//		String url = "http://orig04.deviantart.net/93d4/f/2007/314/9/5/audrey_tautou_by_shimoda7.jpg";
		String url = "http://pic36.nipic.com/20131230/1081324_162447228136_2.jpg";
		String destpath = "D:/b.jpg";
		int threadCount = 3;
		
		FileDownloader downloader = new FileDownloader(url, destpath, threadCount);
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
