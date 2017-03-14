package com.coderising.download.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	HttpURLConnection conn;
	public ConnectionImpl(HttpURLConnection conn) {
		this.conn = conn;
	}
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
	    RandomAccessFile accessFile = new RandomAccessFile("美女.jpg", "rw"); 
	    accessFile.seek(startPos);// 设置操作文件的入点
	    System.out.println(startPos+"hhkjh"+endPos);
        InputStream is = conn.getInputStream(); 
        int i = getContentLength();
        byte[] bytes = new byte[i];//这里设置缓冲区的大小 ，需要的内存；  
        int len = 0;  
        while((len=is.read(bytes))!=-1){    //当读取到block或者是文件末尾的时候，都会返回-1；，表示 没有数据可读取  
            //读取到文件内容之后我们就可以写入本地文件了。   
        	accessFile.write(bytes,0,len);  
        }
        accessFile.close();
        is.close();  
		return bytes; 

	}

	@Override
	public int getContentLength() {
		 int filesize = conn.getContentLength();  
		return filesize;
	}

	@Override
	public void close() {
		
	}

}