package com.dudy.learn01.download;


import com.dudy.learn01.coderising.download.FileDownloader;
import com.dudy.learn01.coderising.download.api.ConnectionManager;
import com.dudy.learn01.coderising.download.api.DownloadListener;
import com.dudy.learn01.coderising.download.impl.ConnectionManagerImpl;
import org.junit.Test;


import java.io.IOException;


public class FileDownloaderTest {
    boolean downloadFinished = false;



	@Test
	public void testDownload() throws IOException {

		//String url = "http://www.lgstatic.com/www/static/mycenter/modules/common/img/tou_42952f6.png";
		String url = "http://img.lanrentuku.com/img/allimg/1606/14665573271238.jpg";
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