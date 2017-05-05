package main.week03.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import main.week03.download.api.ConnectionManager;
import main.week03.download.api.DownloadListener;
import main.week03.download.impl.ConnectionManagerImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileDownloaderTest {
	boolean downloadFinished = false;
	String _url = "http://images2015.cnblogs.com/blog/610238/201604/610238-20160421154632101-286208268.png";
	String _filePath = "/";
	@Before
	public void setUp() throws Exception {
		File file = new File(".");
		
		String packageName = this.getClass().getPackage().getName();
		// 把包名转化成路径的一部分
		packageName = packageName.replace('.', '/');

		_filePath = file.getCanonicalPath() + "/src/" + packageName + "/" + "test.jpg";
		try{ 
		    System.out.println(file.getCanonicalPath());//获取标准的路径 
		    System.out.println(file.getAbsolutePath());//获取绝对路径 
		    System.out.println(file.getPath());
		    System.out.println(packageName);
		}catch(Exception e){}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPath(){}
	
	@Test
	public void testDownload() throws IOException {
		
		FileDownloader downloader = new FileDownloader(_url, _filePath);

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
