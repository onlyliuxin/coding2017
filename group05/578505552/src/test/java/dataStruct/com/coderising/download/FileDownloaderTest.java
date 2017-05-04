package dataStruct.com.coderising.download;

import dataStruct.com.coderising.download.api.ConnectionManager;
import dataStruct.com.coderising.download.api.DownloadListener;
import dataStruct.com.coderising.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		
		String url = "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D360/sign=9bb90992550fd9f9bf17536f152cd42b/9a504fc2d5628535959cf4cf94ef76c6a6ef63db.jpg";

		FileDownloader downloader = new FileDownloader(url);
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		downloader.setListener(new DownloadListener() {
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
