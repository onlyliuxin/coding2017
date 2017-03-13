package com.ace.download;


import com.ace.download.api.ConnectionManager;
import com.ace.download.api.DownloadListener;
import com.ace.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileDownloaderTest {
	static boolean downloadFinished = false;
	/*@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}*/

	public static void main(String[] args){
		String url = "http://www.yaojingweiba.com/uploads/donghua/yaojingdeweibamanhua.jpg";

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
	}

	/*@Test
	public void testDownload() {
		
		String url = "http://localhost:8080/test.jpg";
		
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
		
		

	}*/

}
