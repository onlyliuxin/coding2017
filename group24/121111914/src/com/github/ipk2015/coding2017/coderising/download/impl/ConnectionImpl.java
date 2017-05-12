package com.github.ipk2015.coding2017.coderising.download.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.github.ipk2015.coding2017.coderising.download.api.Connection;
import com.github.ipk2015.coding2017.coderising.download.api.DownloadListener;


public class ConnectionImpl implements Connection{
	private String path;
	public static int threadCount=0;
	public ConnectionImpl(String path){
		super();
		this.path=path; 
	}
	

	@Override
	public int getContentLength()  {
		URL url;
		int length=0;
		try {
			url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(3000);
			int code = conn.getResponseCode();
			System.out.println("code:"+code+"");
			if(code == 200){
				// 获得服务器端文件的大小
				length = conn.getContentLength();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return length;
	}

	@Override
	public void close() {
		
		
	}
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		return null;
	}


	@Override
	public void read(int startPos, int endPos,int threadId,DownloadListener listener) throws IOException {
		URL url=new URL(path);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(3000);
		conn.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);
		int code=conn.getResponseCode();
		if(code==206){
			InputStream inputStream = conn.getInputStream();
			int lastIndexOf = path.lastIndexOf("/");
			RandomAccessFile randomAccessFile = new RandomAccessFile(path.substring(lastIndexOf+1),"rwd");
			RandomAccessFile tempPositionFile = new RandomAccessFile(threadId+".txt","rwd");
			randomAccessFile.seek(startPos);
			int length=-1,total=0;
			byte[] buffer=new byte[1024*500];
			while((length=inputStream.read(buffer))!=-1){
				randomAccessFile.write(buffer,0,length);
				total=total+length;
				tempPositionFile.write(((startPos+total)+"").getBytes());
			}
			tempPositionFile.close();
			randomAccessFile.close();
			inputStream.close();
			synchronized (ConnectionImpl.class) {
				//把当前线程的临时文件删除
				File file = new File(threadId+".txt");
				if(file.exists()){
					file.delete();
				}
				threadCount--;
				if(threadCount==0){
					listener.notifyFinished();
				}
			}
		}
	}

}
