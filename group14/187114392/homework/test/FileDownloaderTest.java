import com.coderising.download.FileDownloader;
import com.coderising.download.FileDownloader_real;
import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionImpl;
import com.coderising.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.CountDownLatch;
import java.io.IOException;

public class FileDownloaderTest {
	boolean downloadFinished = false;
	int thread_count;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFirstHttpGet() {

		String url = "http://127.0.0.1/test/climb.jpg";

		ConnectionManager cm = new ConnectionManagerImpl();
		Connection conn = null;
		try {
			conn = cm.open(url);
			Integer content_length = conn.getContentLength();
		} catch (ConnectionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDownload() {
		thread_count = 10;
//		String url = "http://localhost:8080/test.jpg";
		CountDownLatch latch = new CountDownLatch(thread_count);

		String url = "http://127.0.0.1/test/climb.jpg";
		String localpath = "G:\\Projects\\187114392\\haha.jpg";
		FileDownloader_real downloader = new FileDownloader_real(url,thread_count,localpath, latch);

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
//		while (!downloadFinished) {
//			try {
//				System.out.println("还没有下载完成，休眠五秒");
//				//休眠5秒
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		System.out.println("下载完成！");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testIdeaJarDownload2() {
		thread_count = 9;
		CountDownLatch latch = new CountDownLatch(thread_count);

		String filename = "idea.jar";
		String url = "http://127.0.0.1/test/" + filename;
		String localpath = "G:\\Projects\\187114392\\" + filename;
		FileDownloader_real downloader = new FileDownloader_real(url,thread_count,localpath, latch);
		ConnectionManager cm = new ConnectionManagerImpl();

		downloader.setConnectionManager(cm);

		downloader.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				downloadFinished = true;
			}

		});
		downloader.execute();
		System.out.println("下载完成！");
	}



}
