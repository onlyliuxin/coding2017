package org.wsc.coderising.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wsc.coderising.download.api.ConnectionManager;
import org.wsc.coderising.download.api.DownloadListener;
import org.wsc.coderising.download.impl.ConnectionManagerImpl;

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
		// 资源位置
		String url = "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg";
		// 创建资源下载器实例
		FileDownloader downloader = new FileDownloader(url);
		// 创建连接管理实例
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		// 生成回调函数
		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}

		});
		// 开始下载
		downloader.execute();
		// 等待多线程下载程序执行完毕
		while (!downloadFinished) {
			try {
				System.out.println("还没有下载完成，休眠五秒");
				// 休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！请刷新项目根目录");
	}

}
