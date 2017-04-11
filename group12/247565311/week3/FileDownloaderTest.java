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
		String url = "http://music.163.com/api/pc/download/latest";
		String path = "D:\\hellp.exe";
		FileDownloader downloader = new FileDownloader(url,path);
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}
		});
		double time = 0;
		try {
			downloader.execute();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while (!downloadFinished) {
			try {
				Thread.sleep(100);//休眠0.1秒
				
				time += 1;
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！耗时："+time/10.0+" 秒。");
	}
}
