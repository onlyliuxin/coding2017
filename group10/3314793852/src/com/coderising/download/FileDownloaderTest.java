	
	package com.coderising.download;
	
	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;
	
	import com.coderising.download.api.ConnectionManager;
	import com.coderising.download.api.DownloadListener;
	import com.coderising.download.impl.ConnectionManagerImpl;
	import com.coderising.download.impl.DownloadListenerImpl;
	
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
			
			String url = "http://localhost:8080/demo/moon.png";
			
			FileDownloader downloader = new FileDownloader(url);
	
		
			ConnectionManager cm = new ConnectionManagerImpl();
			downloader.setConnectionManager(cm);
			
			
			downloader.setListener(new DownloadListener() {
				@Override
				public void notifyFinished() {
					downloadFinished = true;
				}
	
			});
	
			 new Thread(new Runnable() {
		            @Override
		            public void run() {
		                while(!downloadFinished){
		                    System.out.println("还没有下载完成，休眠五秒");
		                    try {
		                        Thread.sleep(5000);
		                    } catch (InterruptedException e) {
		                        e.printStackTrace();
		                    }
		                }
		               System.out.println("下载完成！");
		            }
		        }).start();
			
			downloader.execute();
		
			
			
			
			// 等待多线程下载程序执行完毕
			
//				while (!downloadFinished) {
//					try {
//						System.out.println("还没有下载完成，休眠五秒");
//						//休眠5秒
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {				
//						e.printStackTrace();
//					}
//				}
//				System.out.println("下载完成！");
			
			
	
		}
	
	}
