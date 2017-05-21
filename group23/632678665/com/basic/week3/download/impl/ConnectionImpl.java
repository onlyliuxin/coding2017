package com.basic.week3.download.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URLConnection;

import com.basic.week3.download.api.Connection;
import com.basic.week3.download.api.ConnectionException;
import com.basic.week3.download.api.ConnectionManager;

public class ConnectionImpl implements Connection{
	private static final String PATH="E://";
	private URLConnection urlConnection;
	private InputStream input;
	private String fileName;
	ConnectionImpl(URLConnection urlConnection,String fileName){
		this.urlConnection=urlConnection;
		this.fileName=fileName;
	}
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		byte [] buffer=new byte [1024];
		input=urlConnection.getInputStream();
		RandomAccessFile raFile=new RandomAccessFile(creatLocalFile().toString(),"rw");
		//读取次数
		int times=(int)((endPos-startPos-1)/1024);
		input.skip(startPos);
		raFile.seek(startPos);
		for(int i=times;i<=times;i++){
			int flag=input.read(buffer);
			raFile.write(buffer,0,buffer.length);
			if(flag<0){
				break;
			}
		}
		return buffer;
	}

	@Override
	public int getContentLength() {
		return urlConnection.getContentLength();
	}

	@Override
	public void close() {
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private File creatLocalFile(){
		File file=new File(PATH+fileName);
		return file;
	}
}
