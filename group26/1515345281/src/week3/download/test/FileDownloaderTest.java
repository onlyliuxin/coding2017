package week3.download.test;

import org.junit.Test;

import week3.download.FileDownloader;
import week3.download.api.DownloadListener;

public class FileDownloaderTest {

	boolean downloadFinished = false;

	@Test
	public void testFileDownloader() {

		/*String url = "http://210.43.133.109:9999/dldir1.qq.com/qqfile/qq/QQ8.9.1/20437/QQ8.9.1.exe";
		String localFile = "e://qq8.exe";*/
		String url="http://www.iqiyi.com/common/flashplayer/20170331/036801ea7a2e24.swf";
		String localFile="e:\\036801ea7a2e24.swf";
		long begin=System.currentTimeMillis();
		FileDownloader downloader = new FileDownloader(url, localFile);
		downloader.setListener(new DownloadListener() {

			@Override
			public void notifyFinished() {//
				downloadFinished = true;
			}
		});

		downloader.execute();

		// 等待多线程下载程序执行完毕
		while (!downloadFinished) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end=System.currentTimeMillis();
		
		long cost=(end-begin)/1000;
		
		System.out.println("下载完成!时间为"+cost+"秒");
	}
}