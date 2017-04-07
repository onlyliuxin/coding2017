package com.github.ipk2015.coding2017.coderising.download;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.ipk2015.coding2017.coderising.download.api.ConnectionManager;
import com.github.ipk2015.coding2017.coderising.download.api.DownloadListener;
import com.github.ipk2015.coding2017.coderising.download.impl.ConnectionManagerImpl;



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
		
		String url = "http://dldir1.qq.com/qqfile/qq/QQ8.9.1/20437/QQ8.9.1.exe";
		int threadCount=3;
		FileDownloader downloader = new FileDownloader(url,threadCount);

	
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		
		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
					downloadFinished = true;
					System.out.println("下载完成啦");
			}

		});

		
		try {
			downloader.execute();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 等待多线程下载程序执行完毕
//		while (!downloadFinished) {
//			try {
//				System.out.println("还没有下载完成，休眠五秒");
//				//休眠5秒
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {				
//				e.printStackTrace();
//			}
//		}
//		System.out.println("下载完成！");

	}

}
