package testcase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.FileDownloader;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;
import com.coderising.helper.Tool;

public class FileDownloaderTest
{
	boolean downloadFinished = false;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {
		String url = "http://windows.php.net/downloads/releases/php-7.0.17-nts-Win32-VC14-x86.zip";
		String uriSHA256 = "d5f559fe2143b408ccb743dbd7af4406ebe4cdd7c2bec5f417a48dd89aa331b0";

		String savePathDir = "D:/App_data/java/";
		String fileName = "php-7.0.17-nts-Win32-VC14-x86.zip";
		String downloadFileName = "D:/App_data/java/php-7.0.17-nts-Win32-VC14-x86.zip";

		FileDownloader downloader = new FileDownloader(url, savePathDir,
				fileName);

		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);

		downloader.setListener(new DownloadListener() {
			public void notifyFinished() {
				downloadFinished = true;
			}
		});

		downloader.execute();

		while (!downloader.isFinished()) {
			try {
				System.out.println("还没有下载完成，休眠五秒");
				// 休眠5秒
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// // 等待多线程下载程序执行完毕
		// while (!downloadFinished) {
		// try {
		// System.out.println("还没有下载完成，休眠五秒");
		// //休眠5秒
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		System.out.println("下载完成！");

		System.out.println(uriSHA256);
		System.out.println(Tool.getSHA256(downloadFileName));
		assertEquals(uriSHA256, Tool.getSHA256(downloadFileName));

	}

}
