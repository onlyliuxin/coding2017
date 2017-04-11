package com.coderising.download;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	boolean downloadFinished = false;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testContentLength() throws Exception{
		ConnectionManager connMan = new ConnectionManagerImpl();
		Connection conn = connMan.open("http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg");		
		Assert.assertEquals(35470, conn.getContentLength());
	}
	
	@Test
	public void testRead() throws Exception{
		
		ConnectionManager connMan = new ConnectionManagerImpl();
		Connection conn = connMan.open("http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg");
		
		byte[] data = conn.read(0, 35469);
		
		Assert.assertEquals(35470, data.length);
		
		data = conn.read(0, 1023);
		
		Assert.assertEquals(1024, data.length);
		
		data = conn.read(1024, 2023);
		
		Assert.assertEquals(1000, data.length);
		
		
		// 测试不充分，没有断言内容是否正确
	}

	@Test
	public void testDownload() {
		
		String url = "http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg";
		
		FileDownloader downloader = new FileDownloader(url, "F:/picture/a.png");

	
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
