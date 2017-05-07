package com.github.HarryHook.coding2017.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.HarryHook.coding2017.download.api.ConnectionManager;
import com.github.HarryHook.coding2017.download.api.DownloadListener;
import com.github.HarryHook.coding2017.download.impl.ConnectionManagerImpl;

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

	String url = "https://www.baidu.com/img/bd_logo.png";

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
		System.out.println("还没有下载完成，休眠两秒");
		// 休眠2秒
		Thread.sleep(2000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	System.out.println("下载完成！");

    }

}
