package week3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import week3.api.ConnectionManager;
import week3.api.DownloadListener;
import week3.impl.ConnectionManagerImpl;

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
		String url = "https://edmullen.net/test/rc.jpg";
		String path = "D:\\hellp.jpg";
		FileDownloader downloader = new FileDownloader(url,path);
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
				Thread.sleep(5000);//休眠5秒
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！");
	}
}
