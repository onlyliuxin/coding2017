package com.github.mrwengq.tid;

import org.junit.After; 
import org.junit.Before;
import org.junit.Test;

import com.github.mrwengq.tid.api.ConnectionManager;
import com.github.mrwengq.tid.api.DownloadListener;
import com.github.mrwengq.tid.impl.ConnectionManagerImpl;

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
		
		//String url = "http://localhost:8080/test.jpg";
		String url = "http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg";
		String fileName = "C:\\Users\\Administrator\\Desktop\\个人文档\\test.jpg";
		FileDownloader downloader = new FileDownloader(url,fileName);

	
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
