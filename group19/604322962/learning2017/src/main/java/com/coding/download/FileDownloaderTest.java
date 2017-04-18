package com.coding.download;

import com.coding.download.api.ConnectionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.coding.download.api.ConnectionManager;
import com.coding.download.api.DownloadListener;
import com.coding.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	boolean downloadFinished = false;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() throws ConnectionException, InterruptedException, ConnectionException {

		String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489601005973&di=e104648a3c8dcaabb18dfb5d98870d84&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fbaike%2Fpic%2Fitem%2F3b292df5e0fe992536be579530a85edf8cb17140.jpg";

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

	void testRead(){

	}
}
