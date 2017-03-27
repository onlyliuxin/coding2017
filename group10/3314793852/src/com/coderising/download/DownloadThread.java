	/*
	 *  打开输入流下载，对应分配的资源。
	 */
	package com.coderising.download;
	
	import java.io.IOException;
	import java.io.RandomAccessFile;
	
	import com.coderising.download.api.Connection;
	
	public class DownloadThread extends Thread{
	
		Connection conn;				//用来打开下载资源的对用的输入流。
		RandomAccessFile currentPart;	//当前线程下载到本地的位置
		int startPos;					//下载起始位置。
		int endPos;						//下载结束位置。
		int hasRead=0;					//已经下载的长度。
		
		//构造器。
		public DownloadThread( Connection conn, int startPos, int endPos,RandomAccessFile currentPart){
			
			this.conn = conn;	
			this.currentPart=currentPart;
			this.startPos = startPos;
			this.endPos = endPos;
		}
		
		
		/*在该方法中要将数据从byte数组写入到文件中.
		 * 要先把数据写入byte数组中，再从数组中把数据写入文件中。
		 */
		public void run(){	
			
			try {
				synchronized(this){
					byte[] buff=new byte[endPos-startPos+1];
					buff=conn.read(startPos, endPos);
					//将字节数组中的数据读入指定文件中。
					for(int i=0;i<buff.length;i++){
						currentPart.write(buff[i]);
						hasRead++;
					}
					//System.out.println("Over");
					currentPart.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
