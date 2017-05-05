package com.coding.basic.homework_03.download;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.homework_03.download.api.ConnectionManager;
import com.coding.basic.homework_03.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	boolean downloadFinished = false;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() throws IOException {
		
		String url = "http://bpic.588ku.com/element_origin_min_pic/16/12/01/b4365c64e8a567afd0ab63285515de55.jpg";
		
		FileDownloader downloader = new FileDownloader(url);
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		downloader.execute();
		
//		downloader.setListener(new DownloadListener() {
//			@Override
//			public void notifyFinished() {
//				downloadFinished = true;
//			}
//		});
		
		
		// 等待多线程下载程序执行完毕
//		while (!downloadFinished) {
//			try {
////				System.out.println(Thread.currentThread().getName());
//				System.out.println("还没有下载完成，休眠五秒");
//				//休眠5秒
////				Thread.sleep(1000);
//			} catch (Exception e) {				
//				e.printStackTrace();
//			}
//		}
		System.out.println("下载完成！文件存放在此项目根目录下");
		
		

	}

}