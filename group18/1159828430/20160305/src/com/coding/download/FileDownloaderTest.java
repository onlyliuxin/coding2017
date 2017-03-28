package com.coding.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.download.api.ConnectionManager;
import com.coding.download.api.DownloadListener;
import com.coding.download.impl.ConnectionManagerImpl;
import com.coding.download.impl.FileUtil;

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
		
		String url = "http://gss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/5ab5c9ea15ce36d33274da5e3cf33a87e950b168.jpg";
		
		FileDownloader downloader = new FileDownloader(url);

	
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		
		FileUtil file = new FileUtil("F:\\test.jpg");
		downloader.setFile(file);
		
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
