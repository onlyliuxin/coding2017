package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;


public class FileDownloader {
	
	String url;
	
	DownloadListener listener;
	
	ConnectionManager cm;
	

	public FileDownloader(String _url) {
		this.url = _url;
		
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
		Connection conn1 = null;
		Connection conn2 = null;
		Connection conn3 = null;
		//老师这里使用了栅栏 cyclicBarrier 替代了我下面这些代码的作用，我写的这堆代码最终也起到的是这样的作用，等待 N个执行完了以后去执行某件事
		try {
			File f = new File("F:\\百度.jpg");
			FileOutputStream os=null;
			try {
				if(!f.exists()){
					f.createNewFile();
					
				}else{
					f.delete();
				}
				 os = new FileOutputStream(f,true);
				 
				 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn1 = cm.open(this.url);
			conn2 = cm.open(this.url);
			conn3 = cm.open(this.url);
			int length = conn1.getContentLength();	
		    
		    
			DownloadThread thread1=new DownloadThread(conn1,0,length/3);
			DownloadThread thread2=new DownloadThread(conn2,length/3,(length/3)*2);
			DownloadThread thread3 =new DownloadThread(conn3,(length/3)*2,length-1);
		
			thread1.start();
			thread2.start();
			thread3.start();
			
			StringBuilder add = new StringBuilder();
			while(true){
				if(!thread1.isAlive()&&add.indexOf("1")<0){
					try {
						os.write(thread1.reads);
						add.append("1");
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		
				if(!thread2.isAlive()&&add.indexOf("2")<0&&add.indexOf("1")==0){
					try {
						os.write(thread2.reads);
						add.append("2");
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
		
				if(!thread3.isAlive()&&add.indexOf("3")<0&&add.indexOf("12")==0){
					try {
						os.write(thread3.reads);
						add.append("3");
						os.flush();
						os.close();
						//这里没明白老师设计的notifyFinished的用法
						listener.notifyFinished(true);
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			
			}
				
		} catch (ConnectionException e) {			
			e.printStackTrace();
		}finally{
			if(conn1 != null){
				conn1.close();
			}
			if(conn2!=null){
				conn2.close();
			}
			if(conn3!=null){
				conn3.close();
			}
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
