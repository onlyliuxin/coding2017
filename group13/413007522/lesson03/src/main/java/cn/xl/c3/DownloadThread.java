package cn.xl.c3;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import cn.xl.c3.DownloadTask.ChildThread;
import cn.xl.c3.api.Connection;

public class DownloadThread extends Thread{

	public static final int STATUS_HASNOT_FINISHED = 0; 
	public static final int STATUS_HAS_FINISHED = 1; 
	public static final int STATUS_HTTPSTATUS_ERROR = 2; 
	Connection conn;
	int startPos;
	int endPos;
	int id;
	DownloadTask task; 
	CountDownLatch latch; 
	File tempFile = null; 
	// 线程状态码
	public int status = ChildThread.STATUS_HASNOT_FINISHED; 

	public DownloadThread( String filePath, Connection conn,int id, int startPos, int endPos, CountDownLatch latch){

		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
		this.latch = latch; 
		this.id  = id;
		try { 
			tempFile = new File(filePath + "_" + id); 
			if(!tempFile.exists()){ 
				tempFile.createNewFile(); 
			} 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 


	}


	public void run(){	

		try {

			InputStream inputStream = null; 
			BufferedOutputStream outputStream = null; 
			int count = 0; 
			long threadDownloadLength = endPos - startPos; 

			try { 
				outputStream = new BufferedOutputStream(new FileOutputStream(tempFile.getPath(), true)); 
			} catch (FileNotFoundException e2) { 
				e2.printStackTrace(); 
			} 

			for(; ; ){ 
				startPos += count; 

				System.out.println("the id="+id+"thread ,startPos:"+startPos);
				System.out.println("the id="+id+"thread ,endPos:"+endPos);
				
				inputStream = conn.getInputStream(startPos,endPos); 

				int len = 0; 
				byte[] b = new byte[1024]; 
				while ((len = inputStream.read(b)) != -1) { 
					outputStream.write(b, 0, len); 
					count += len; 

					// 每读满5000个byte，往磁盘上flush一下
					if(count % 5000 == 0){ 
						outputStream.flush(); 
					} 
				} 

				System.out.println("count is " + count); 
				if(count >= threadDownloadLength){ 
					//hasFinished = true; 
				} 
				outputStream.flush(); 
				outputStream.close(); 
				inputStream.close(); 

				this.status = this.STATUS_HAS_FINISHED;
				//System.out.println("Thread " + id + " finished."); 
				latch.countDown(); 
				break; 

			} 


			this.status = this.STATUS_HAS_FINISHED;
			latch.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
