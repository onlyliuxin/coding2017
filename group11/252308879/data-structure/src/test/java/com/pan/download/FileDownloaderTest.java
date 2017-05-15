package com.pan.download;

import com.pan.download.api.ConnectionManager;
import com.pan.download.impl.ConnectionManagerImpl;
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
		String url = "http://pic17.nipic.com/20111102/3707281_235344313129_2.jpg";

		FileDownloader downloader = new FileDownloader(url);
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		downloader.setListener(() -> downloadFinished = true);
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
