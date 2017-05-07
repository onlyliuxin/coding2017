package com.coderising.download.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.FileDownloader;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

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
		/*题 一般的时候是网络连接的问题。还有你可以设置一下 服务器的超时时间。有的可能是三十秒。你试试更长点的。
		 * 还有一种可能性是。你程序里创建了很多connection 但是没有关闭调。现在数据库处于半死状态，然后连接超时。
		 * 你ping的是服务器的ip吗？ 你可以用plsql检验一下你的网络是否通。还有配置服务器数据源的时候 如果能配置成功，
		 * 那网络也没问题。 
		*/
		//String url = "http://www.hinews.cn/pic/0/13/91/26/13912621_821796.jpg";
		
		String url = "http://images2015.cnblogs.com/blog/610238/201604/610238-20160421154632101-286208268.png";
		
		FileDownloader downloader = new FileDownloader(url,"e:\\项目练手\\test.jpg");

	
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
