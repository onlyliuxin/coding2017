package test.com.java.xiaoqin.download;

import com.java.xiaoqin.download.FileDownloader;
import com.java.xiaoqin.download.api.ConnectionManager;
import com.java.xiaoqin.download.api.DownloadListener;
import com.java.xiaoqin.download.impl.ConnectionManagerImpl;
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
		
		String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489345050113&di=f1a30d0eac1129de3fadab88fa128008&imgtype=0&src=http%3A%2F%2Ffile.nanbeiyou.com%2FRequisite%2Fmid%2F1c62cf145f5043c.jpg";
		
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
