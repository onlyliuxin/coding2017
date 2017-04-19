package com.coderising.download.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	public URL url;
	public int length;
	public InputStream inputStream=null;
	public RandomAccessFile randomAccessFile=null;
	
	
	public  ConnectionImpl() {
		// TODO Auto-generated constructor stub
	}
	public ConnectionImpl(URL url){
		try {
			this.url=url;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public byte[] read(int startPos, int endPos,File file) throws IOException {
		
		URLConnection uc=url.openConnection();
		uc.setRequestProperty("Range", "bytes="+startPos+"-"+endPos);//指定当前一块要获取的开始到结束位置
		
		inputStream=uc.getInputStream();
		randomAccessFile=new RandomAccessFile(file, "rwd");
		randomAccessFile.seek(startPos);//设置写入file文件时从哪里开始写
		
		byte[] data=new byte[2048];
		int hasread=0;
		
		while(-1 != (hasread = inputStream.read(data))){
			randomAccessFile.write(data, 0, hasread);
		}
		return null;
	}

	@Override
	public int getContentLength() {
		try {
			URLConnection connection=url.openConnection();
			length=connection.getContentLength();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return length;
	}

	@Override
	public void close() {
		if(null!=inputStream){
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(null!=randomAccessFile){
			try {
				randomAccessFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
