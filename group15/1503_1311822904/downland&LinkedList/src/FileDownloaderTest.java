import api.DownloadListener;
import impl.ConnectionManagerImpl;
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
		
		String url = "http://h.hiphotos.baidu.com/baike/s%3D220/sign=d37711ea2b2eb938e86d7df0e56385fe/32fa828ba61ea8d37a8f660f930a304e251f580f.jpg";
		
		FileDownloader fileDownloader = new FileDownloader(url);

		fileDownloader.setConnectionManager(new ConnectionManagerImpl());
		
		fileDownloader.setDownloadListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}

		});

		
		fileDownloader.execute();
		
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
