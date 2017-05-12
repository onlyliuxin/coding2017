package rui.study.coding2017.jobs3.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rui.study.coding2017.jobs3.download.FileDownloader;
import rui.study.coding2017.jobs3.download.api.ConnectionManager;
import rui.study.coding2017.jobs3.download.api.DownloadListener;
import rui.study.coding2017.jobs3.download.impl.ConnectionManagerImpl;


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
		
		String url = "http://sw.bos.baidu.com/sw-search-sp/software/952c9d6e73f50/QQ_8.9.20029.0_setup.exe";

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
