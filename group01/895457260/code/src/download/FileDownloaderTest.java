package download;

import download.FileDownloader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import download.api.ConnectionManager;
import download.api.DownloadListener;
import download.impl.ConnectionManagerImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		
//		String url = "http://localhost:8080/test.jpg";
//		String url = "file:///E:/Video/download/88993.mp4";
		String url = "file:///E:/Pictures/Clannad/Clannad高清图片/38.jpg";

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
}
