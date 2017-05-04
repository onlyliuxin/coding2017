package com.coderising.download;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import com.coderising.download.api.Connection;

public class DownloadThread extends Thread {

	Connection conn;
	int startPos;
	int endPos;
	/* 文件路径 */
	String downloadPath;
	/*临时文件*/
	File tempFile;
	/*文件后缀名称*/
	String sufferName;
	/*当前线程下载量*/
	volatile int downloadSize;

	public DownloadThread(String downloadPath, Connection conn, int startPos,
			int endPos) {

		this.downloadPath = downloadPath;
		this.conn = conn;
		this.startPos = startPos;
		this.endPos = endPos;
	}

	public void run() {

		try {
			
			//修改后缀名称,并保存正确后缀名
			String name = reSufferNameAndStore(conn.getDownloadName());
			//创建临时文件
			File file = new File(downloadPath+"/"+name);
			if(!file.exists()){
			    file.createNewFile();
            }	
			//初始化属性
			tempFile = file;
			//获取指定文件段的下载流
			InputStream in = conn.getDownloadStream(startPos, endPos);
			if(in == null){//重新请求连接
				run();
			}
			//随机访问文件流
			RandomAccessFile raf = new RandomAccessFile(tempFile, "rwd"); 
			//随机写文件的时候从哪个位置开始写  
			raf.seek(startPos);//定位文件 
			//开始写入
			byte[] buffer = new byte[1024]; 
	        int length = -1; 
	        while ((length = in.read(buffer)) != -1) { 
	        	raf.write(buffer, 0, length);
	        	downloadSize += length;
	        }	 
			raf.close();
		} catch (Exception e) {
			run();
			e.printStackTrace();			
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * 修改后缀名称,并保存正确后缀名
	 * @param name
	 * @return
	 */
	private String reSufferNameAndStore(String name){
		
		sufferName = name.substring(name.lastIndexOf("."));
		name = name.substring(0,name.lastIndexOf("."))+".zj♥yy";
		return name;
	}
}