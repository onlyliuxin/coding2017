package com.sprint.download;

import com.sprint.download.api.ConnectionManager;
import com.sprint.download.api.DownloadListener;
import com.sprint.download.impl.ConnectionManagerImpl;

import org.junit.Assert;
import org.junit.Test;
public class FileDownloaderTest {
	boolean downloadFinished = false;
	
	@Test
	public void testDownload() {
		String url = "http://images2015.cnblogs.com/blog/610238/201604/610238-20160421154632101-286208268.png";
		FileDownloader downloader = new FileDownloader(url, "/home/sprint/xxx/test.jpg");// 保存地址时我的本地地址

		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		
		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = false;
			}
		});
		
		downloader.execute();
	
		//等待多线程下载
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
