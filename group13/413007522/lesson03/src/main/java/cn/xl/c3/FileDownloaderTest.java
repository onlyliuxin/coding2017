package cn.xl.c3;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.xl.c3.api.ConnectionManager;
import cn.xl.c3.api.DownloadListener;
import cn.xl.c3.impl.ConnectionManagerImpl;

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

		//String url = "http://image11.m1905.cn/uploadfile/2015/0211/thumb_1___3_20150211064226697882.jpg";
		String url = "http://imgadmin.voole.com/img/pic/2017/03/21/1000/2017032117552710008ww5f.jpg";

		String filePath = "E:/test/";

		FileDownloader downloader = new FileDownloader(url,filePath);

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
