package com.coding.week3;

import com.coding.week3.download1.api.ConnectionManager;
import com.coding.week3.download1.api.DownloadListener;
import com.coding.week3.download1.impl.ConnectionManagerImpl;
import com.coding.week3.download1.FileDownloader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


public class FileDownloaderTest {
	boolean downloadFinished = false;
    static final int DEFAULT_THREADS_NUM = 5;

    @Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() throws IOException {

		String url =  "http://101.95.48.97:8005/res/upload/interface/apptutorials/manualstypeico/6f83ce8f-0da5-49b3-bac8-fd5fc67d2725.png";
//		String url =  "http://download.oracle.com/otn-pub/java/jdk/8u121-b13/e9e7ea248e2c4826b92b3f075a80e441/jdk-8u121-linux-arm32-vfp-hflt.tar.gz";
//		String url =  "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490808670106&di=48aa6fb7af641f0cb6f9e19120b60c7c&imgtype=0&src=http%3A%2F%2Fwww.ntjoy.com%2Fliv_loadfile%2Fhealth%2Fdzcs%2Fnvr%2Ffold1%2F1360480639_97304600.jpg";
//		String url =  "https://download.jetbrains.com/idea/ideaIU-2017.1.exe";
//		String url =  "https://nodejs.org/dist/v6.10.1/node-v6.10.1-win-x64.zip";
//		String url =  "http://download.oracle.com/otn-pub/java/jdk/8u121-b13-demos/e9e7ea248e2c4826b92b3f075a80e441/jdk-8u121-windows-x64-demos.zip";
		String path = new File("").getAbsolutePath();
		String filename = url.substring(url.lastIndexOf("/"), url.length());
		 filename = path +File.separator + filename;

		FileDownloader downloader = new FileDownloader(DEFAULT_THREADS_NUM, filename, url);
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}

		});

		
		downloader.execute();
		
//		// 等待多线程下载程序执行完毕
//		while (!downloadFinished) {
//			try {
//				System.out.println("还没有下载完成，休眠五秒");
//				//休眠5秒
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("下载完成！");
		
		

	}


	@Test
	public void lenTest(){
		int lastLen = 0;
		int length = 10232;
		int nThread = 10;
		int perLenOfThread = 0;

		if ( length % nThread == 0)
			perLenOfThread = length / nThread;
		else {
			lastLen = length % nThread;
			perLenOfThread = (length - lastLen) / nThread;
		}
		Assert.assertEquals(perLenOfThread, 1023);
		Assert.assertEquals(lastLen, 2);

		Thread[] threads = new Thread[nThread+1];
		if ( length % nThread == 0) {
			perLenOfThread = length / nThread;
		}
		else {
			lastLen = length % nThread;
			perLenOfThread = (length - lastLen) / nThread;
		}
		for (int i = 0; i <= nThread; i++) {
			if ( i < nThread) {
				System.out.println("startPos: " + perLenOfThread * i);
				System.out.println("endPos: " + (perLenOfThread * (i + 1) - 1));
			} else {
				System.out.println("startPos: " + perLenOfThread * nThread);
				System.out.println("endPos: " + (length - 1));
			}
		}
	}
}
