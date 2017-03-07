package download;

import download.api.ConnectionManager;
import download.api.DownloadListener;
import download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	boolean downloadFinished = false;

	public void setUp() throws Exception {
	}

	
	public void tearDown() throws Exception {
	}

	
	public void testDownload() {
		
		String url = "http://localhost:8080/test.jpg";
		
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
