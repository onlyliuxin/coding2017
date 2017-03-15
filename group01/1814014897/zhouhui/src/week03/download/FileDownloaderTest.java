package week03.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import week03.download.api.ConnectionManager;
import week03.download.api.DownloadListener;
import week03.download.impl.ConnectionManagerImpl;

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
		
		String url = "http://hiphotos.baidu.com/240728057/pic/item/6a50e38242aad8f60cf4d2b3.jpg";
		//String url = "http://localhost/yuanshi.jpg";    //Apache本地服务器下可测试
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
