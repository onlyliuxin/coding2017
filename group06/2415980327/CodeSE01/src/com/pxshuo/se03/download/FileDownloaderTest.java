package com.pxshuo.se03.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pxshuo.se03.download.api.ConnectionManager;
import com.pxshuo.se03.download.api.DownloadListener;
import com.pxshuo.se03.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	boolean downloadFinished = false;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) {
		FileDownloaderTest test = new FileDownloaderTest();
		test.testDownload();
	}
	
	public void testDownload() {
		
		String url = "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/3c6d55fbb2fb4316352d920a22a4462309f7d394.jpg";
		String filePath = "C://Users//Pxshuo//Desktop//test.png";
		
		FileDownloader downloader = new FileDownloader(url,filePath);

	
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
