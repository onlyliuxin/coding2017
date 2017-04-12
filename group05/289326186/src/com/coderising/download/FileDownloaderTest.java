package com.coderising.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void testDownload() {
//		String url = "http://localhost:8080/haha.jpg";
		String url = "http://article.fd.zol-img.com.cn/t_s501x2000/g1/M07/0C/0D/Cg-4jVOpQWmIVPnYAAHFv0U3gXkAAOjOwAtyyUAAcXX661.jpg";
		FileDownloader downloader = new FileDownloader(url);
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		
		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}
		});
		
		//执行下载
		downloader.execute();
		// 等待多线程下载程序执行完毕
		while (!downloadFinished) {
			try {
			    if(downloader.isFinished()){
	                downloader.listener.notifyFinished();
	            }
				System.out.println("还没有下载完成，休眠5秒");
				//休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		System.out.println("----下载完毕----");
	}

}