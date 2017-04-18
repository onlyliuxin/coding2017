package com.coderising.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	boolean downloadFinished = false; //是否下载完
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {
		
		String url = "http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg";
		
		FileDownloader downloader = new FileDownloader(url, "d:/13912621_821796.jpg");

		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);  //设置连接管理器
		
		downloader.setListener(new DownloadListener() {  //设置监听器
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}

		});

		
		downloader.execute(); //执行下载
		
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
		System.out.println("Down!!!");
		
		

	}

}
