package com.github.PingPi357.coding2017.homework3.download;


//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import com.github.PingPi357.coding2017.homework3.download.api.ConnectionManager;
import com.github.PingPi357.coding2017.homework3.download.api.DownloadListener;
import com.github.PingPi357.coding2017.homework3.download.impl.ConnectionManagerImpl;

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
		
		String url = "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=java&step_word=&hs=0&pn=6&spn=0&di=162779875940&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1874964001%2C375036351&os=1257867049%2C51508497&simid=3335883052%2C31514943&adpicid=0&lpn=0&ln=1924&fr=&fmq=1489319465954_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fwww.5itjob.com%2Fitjob%2Fuploads%2F160515%2F2-160515234130G1.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bctp35k_z%26e3Bv54AzdH3Ftg1jx_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0";
		
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
