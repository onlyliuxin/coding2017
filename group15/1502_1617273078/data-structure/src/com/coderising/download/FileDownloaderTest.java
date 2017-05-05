package com.coderising.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

import java.io.IOException;

public class FileDownloaderTest {
	boolean downloadFinished = false;
/*	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}*/

	@Test
	public void testDownload() throws IOException {
		
//		String url = "http://localhost:8080/test.jpg";
		String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489083998807&di=5bd3803161f80b5f9ef6f3277398c4ac&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fbba1cd11728b47101489df48c0cec3fdfd03238b.jpg";
		
		FileDownloader downloader = new FileDownloader(url);

	
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		
		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}

		});

		
		downloader.execute(5);
		
		// 等待多线程下载程序执行完毕
		while (!downloadFinished) {
			try {
				System.out.println("download is not finished，thread sleep five seconds");
				//休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		System.out.println("the download finished !");
		
		

	}

}
