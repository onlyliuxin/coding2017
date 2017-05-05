package download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import thread.download.FileDownloader;
import thread.download.api.ConnectionManager;
import thread.download.api.DownloadListener;
import thread.download.impl.ConnectionManagerImpl;


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
		
		String url = "https://www.baidu.com/img/bd_logo1.png";
		String fileLocation = "D:\\Tee\\JavaLearnin\\test.png";
		FileDownloader downloader = new FileDownloader(url, fileLocation);

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
