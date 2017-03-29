package com.coderising.download;

import org.junit.Test;

import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {

	private boolean downloadFinished = false;

	@Test
	public void testDownloader() {
		String url = "http://127.0.0.1:8081/nexus/service/local/repositories/thirdparty/content/com/itrus/json/itrus-json/1.0/itrus-json-1.0.jar";
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
		while (!downloadFinished) {
			try {
				System.out.println("还没有下载完成，休眠五秒");
				// 休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！");
	}
}
