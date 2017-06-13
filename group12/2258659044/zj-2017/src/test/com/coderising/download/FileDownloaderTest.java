package test.com.coderising.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.FileDownloader;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	
	int persent = 0;
	
	String downloadSpeed ="0";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {
		
		//String url = "http://localhost:8080/test.jpg";
		
		String url = "http://sw.bos.baidu.com/sw-search-sp/software/89179b0b248b1/QQ_8.9.20026.0_setup.exe";
		//String url = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1233198091,3919880155&fm=116&gp=0.jpg";
		FileDownloader downloader = new FileDownloader(url);
	
		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);
		downloader.setDownloadPath("C:/Users/ZJ/Desktop");
		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished(int percent,String speed) {
				persent = percent;
				downloadSpeed = speed;
			}

		});
		
		downloader.execute();
		
		// 等待多线程下载程序执行完毕
		int temp = -1;
		while (true) {
			try {
				if(temp!=persent){
					temp = persent;
					System.out.println("已下载"+persent+"%，下载速度为："+downloadSpeed+"。");
				}
				if(persent == 100){
					break;
				}
				//休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！");
				
	}
}
