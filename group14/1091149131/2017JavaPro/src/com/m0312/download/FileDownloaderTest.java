package com.m0312.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.m0312.download.api.ConnectionManager;
import com.m0312.download.api.DownloadListener;
import com.m0312.download.impl.ConnectionManagerImpl;

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
		
//		String url = "http://localhost:8080/test.jpg";
//		String url = "http://120.76.28.31/fileroot/pdf/test/testReport-20160803185703.pdf";
//		String url = "http://127.0.0.3:8082/testdownload.pdf";
		String url = "http://127.0.0.3:8082/applogo.png";
		
		FileDownloader downloader = new FileDownloader(url);

	
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
		/**
		 * 网络资源的大小4812
			开始读[0,1603]
			开始读[1604,3207]
			开始读[3208,4811]
			下载完成！
		 */
		

	}

}
