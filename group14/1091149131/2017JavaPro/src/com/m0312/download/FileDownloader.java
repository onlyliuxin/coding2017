package com.m0312.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

import com.m0312.download.api.Connection;
import com.m0312.download.api.ConnectionException;
import com.m0312.download.api.ConnectionManager;
import com.m0312.download.api.DownloadListener;
import com.test.downfile.DownThread;


public class FileDownloader {
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	private static final int THREAD_NUM = 3;

	//定义几个线程去下载  
    final int DOWN_THREAD_NUM = 3;  
    final String OUT_FILE_NAME = "e:/testfile/down.png";  
    InputStream[] isArr = new InputStream[DOWN_THREAD_NUM];  
    RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];  
    
	public FileDownloader(String _url) {
		this.url = _url;
		
	}
	private void createPlaceHolderFile(String fileName, int contentLen) throws IOException{
		
		RandomAccessFile file = new RandomAccessFile(fileName,"rw");
		
		for(int i=0; i<contentLen ;i++){
			file.write(0);
		}
		
		file.close();
	}
	public void execute(){
		// 在这里实现你的代码， 注意： 需要用多线程实现下载
		// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
		// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
		// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
		//     线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
		// 具体的实现思路：
		// 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
		// 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
		// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
		// 3. 把byte数组写入到文件中
		// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
		
		// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
		Connection conn = null;
		try {
			
			conn = cm.open(this.url);
			
			int length = conn.getContentLength();
			File desc=new File(OUT_FILE_NAME);
			if(desc.exists()){
				desc.delete();
			}
			
			String filename=url.substring(url.lastIndexOf("/"));
			String descFilePath="E://testfile//"+filename;
			createPlaceHolderFile(OUT_FILE_NAME, length);		
			
			CyclicBarrier barrier=new CyclicBarrier(THREAD_NUM,new Runnable() {
	            @Override
	            public void run() {
	            	listener.notifyFinished();
	            }
	        });
			/*int every=length/3;
			new DownloadThread(conn,0,every-1,descFilePath,barrier).start();
			new DownloadThread(conn,every,every*2-1,descFilePath,barrier).start();
			new DownloadThread(conn,every*2,length-1,descFilePath,barrier).start();*/
			
			
			isArr[0] = conn.getUrlCon().getInputStream();
            int fileLen = conn.getContentLength();  
            System.out.println("网络资源的大小" + fileLen);  
              
            // 每线程应该下载的字节数  
            long numPerThred = fileLen / DOWN_THREAD_NUM;  
            // 整个下载资源整除后剩下的余数取模  
            long left = fileLen % DOWN_THREAD_NUM;  
            int start=0;
            int end=0;
            for (int i = 0; i < DOWN_THREAD_NUM; i++) {  
            	
                // 为每个线程打开一个输入流、一个RandomAccessFile对象，  
                // 让每个线程分别负责下载资源的不同部分。  
                //isArr[0]和outArr[0]已经使用，从不为0开始  
                
                // 分别启动多个线程来下载网络资源  
                if (i == DOWN_THREAD_NUM - 1) {  
                    // 最后一个线程下载指定numPerThred+left个字节  
                	start=(int) (i * numPerThred);
                	end=(int) ((i + 1) * numPerThred  
                            + left-1);
                } else {  
                    // 每个线程负责下载一定的numPerThred个字节  
                	start=(int) (i * numPerThred);
                	end=(int) ((i + 1) * numPerThred)-1;
                   
                } 
                new DownloadThread(conn, start, end,OUT_FILE_NAME,barrier).start();
                //Thread.sleep(1000);
            }  
			
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			conn.close();
		}
		
		
		
		
	}
	
	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	
	
	public void setConnectionManager(ConnectionManager ucm){
		this.cm = ucm;
	}
	
	public DownloadListener getListener(){
		return this.listener;
	}
	
}
