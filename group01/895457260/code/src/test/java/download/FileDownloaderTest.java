package download;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import download.api.ConnectionManager;
import download.impl.ConnectionManagerImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDownloaderTest {
	private boolean downloadFinished = false;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {

		String url = "file:///E:/Video/download/88993.mp4";
//		String url = "file:///E:/Pictures/Clannad/Clannad高清图片/38.jpg";
//		String url = "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg";

		FileDownloader downloader = null;
		try {
			downloader = new FileDownloader(url);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail("wrong url");
		}

		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);

		downloader.setOnCompleteListener(() -> {
			downloadFinished = true;
			System.out.println("下载完成");
		});
		downloader.setOnFailListener(() -> {
			downloadFinished = true;
			System.out.println("下载失败");
		});

		downloader.execute();
		
		// 等待多线程下载程序执行完毕
		while (!downloadFinished) {
			try {
				System.out.println("正在下载…………");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean actualContent(File downloaded, File source) {
		String expected = readFile(downloaded);
		String actual = readFile(source);
		return expected.equals(actual);
	}

	private String readFile(File file) {
		int n;
		StringBuilder builder = new StringBuilder();
		byte[] buf = new byte[1024];
		try {
			InputStream is = new FileInputStream(file);
			while ((n = is.read(buf)) != -1) {
				for (int i = 0; i < n; ++i) {
					builder.append(String.format("%d", buf[i]));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}
