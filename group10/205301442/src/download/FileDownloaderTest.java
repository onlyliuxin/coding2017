package com.coderising.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;
import com.coderising.download.impl.DoloadListenerImpl;

public class FileDownloaderTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {
		
		String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489320877777&di=0602ac45c01a727564917f688e3d4ad2&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140521%2F18810499_233249416000_2.jpg";
		
		FileDownloader downloader = new FileDownloader(url);
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		DoloadListenerImpl lister = new DoloadListenerImpl();
		downloader.setListener(lister);
		downloader.execute();
		
		
		// 等待多线程下载程序执行完毕
		while (!downloader.getListener().getIsFinished()) {
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
