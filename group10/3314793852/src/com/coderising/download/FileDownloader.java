	
	package com.coderising.download;
	
	import java.io.RandomAccessFile;

	import com.coderising.download.api.Connection;
	import com.coderising.download.api.ConnectionException;
	import com.coderising.download.api.ConnectionManager;
	import com.coderising.download.api.DownloadListener;
	import com.coderising.download.impl.DownloadListenerImpl;
	
	
	public class FileDownloader {
		
		String url;						//URL地址
		DownloadListener listener;		//下载完成通知
		ConnectionManager cm;			//打开URL链接
		String targetFile="E:/moon.png";				//指定所下载文件的保存位置
	
		public FileDownloader(String _url) {
			this.url = _url;
		}
		
		public void execute(){
			// 在这里实现你的代码， 注意： 需要用多线程实现下载
			// 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
			// (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
			// (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
			//     线程都执行完以后， 调用DownloadListener的notifiedFinished方法， 这样客户端就能收到通知。
			// 具体的实现思路：
			// 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
			// 2. 至少启动3个线程下载，  注意每个线程需要先调用ConnectionManager的open方法
			// 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
			// 3. 把byte数组写入到文件中
			// 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
			
			// 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
			Connection conn = null;
			try {
				
				conn = cm.open(this.url);						//ConnectionManager的对象的open方法返回一个Connection对象
				int length = conn.getContentLength();			//获得文件的总长度。
				//System.out.println(length);
				//创建一个文件，用来保存下载的资源。
				RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
				//设置本地文件的大小。
				file.setLength(length);
				
				file.close();
				
				
				int numberOfThread=4;							//线程的个数，自定义，当前定为4.
				
				int eachLength=length/4+1;						//每个线程的下载的长度。
				RandomAccessFile[] currentPart=new RandomAccessFile[4];
				DownloadThread[] thread=new DownloadThread[4];
				for(int i=0;i<numberOfThread-1;i++){				//确定子线程的开始和结尾点，并启动子线程。
					currentPart[i]= new RandomAccessFile(targetFile, "rw");
					currentPart[i].seek(0+eachLength*i);
					thread[i]=new DownloadThread(conn,0+eachLength*i,eachLength+eachLength*i-1,currentPart[i]);
					thread[i].start();	//启动线程。
					//System.out.println(i);
				}
				currentPart[3]= new RandomAccessFile(targetFile, "rw");
				currentPart[3].seek(eachLength*3);
				thread[3]=new DownloadThread(conn,eachLength*3,length-1,currentPart[3]);
				thread[3].start();		//最后一个线程
				
				//统计多条线程已经下载的总大小。
				
				
				
				int sumSize=0;
				//检测是否下载完毕
				while(true){
					
					for(int j=0;j<numberOfThread;j++){
						sumSize+=thread[j].hasRead;
					}
					//System.out.println("sumSize"+sumSize);
					if(sumSize==length){
						listener.notifyFinished();
						//System.out.println("完成");
						System.out.println("下载完成！");
						break;
					}
					sumSize=0;
//					Thread.sleep(10000);
				}
				
			} catch (Exception e) {			
				e.printStackTrace();
			}finally{
//				if(conn != null){
//					conn.close();
//				}
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
