package com.coderising.download;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
	boolean downloadFinished = false;
	  private static Integer pages=1; // 网页数
	     
	    private static boolean exeFlag=true; // 执行标识
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDownload() {
		
		String url="http://mirrors.hust.edu.cn/apache/tomcat/tomcat-8/v8.0.41/bin/apache-tomcat-8.0.41-windows-x64.zip";
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
				//Thread.sleep(5000);
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		System.out.println("下载完成！");
		
		

	}
	
	//http://image.so.com/v?ie=utf-8&src=hao_360so&q=%E9%AB%98%E5%9C%86%E5%9C%86&correct=%E9%AB%98%E5%9C%86%E5%9C%86&fromurl=http%3A%2F%2Fwww.cesiu.org.cn%2Fomdsj%2F2010674.html&gsrc=1#multiple=0&dataindex=57&id=537876d111c8adfec7fbda2b80a4f67b
	@Test
	public void testOpen() throws ConnectionException {

	  
	     
		ExecutorService executorService=Executors.newFixedThreadPool(10); // 创建ExecutorService 连接池创建固定的10个初始线程
        
        while(exeFlag){
            if(pages<=100){
//                executorService.execute(new Runnable(){
// 
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                    	System.out.println(Thread.currentThread().getName());
//                      System.out.println("爬取了第"+pages+"网页...");
//                        pages++;
//                    }
//                     
//                });
                new Runnable(){
                	 
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                    	System.out.println(Thread.currentThread().getName());
                      System.out.println("爬取了第"+pages+"网页...");
                        pages++;
                    }
                     
                }.run();;
               
            }else{
                if(((ThreadPoolExecutor)executorService).getActiveCount()==0){ // 活动线程是0
                    executorService.shutdown(); // 结束所有线程
                    exeFlag=false;
                    System.out.println("爬虫任务已经完成");
                }
            }
            try {
              // Thread.sleep(2000); // 线程休息0.1秒
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
       
	}
}
