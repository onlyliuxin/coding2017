package com.coderising.download.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	
	private String url = null;
	private InputStream in = null;


	public ConnectionImpl(String url) {
		this.url = url;
		
		
	}

	public byte[] read(int startPos, int endPos) throws IOException {
		
		try {
			if(url==null||url.trim()==""){
				return null;
			}
			
			URL u = new URL(url);
			in = u.openStream();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(in==null){
			return null;
		}
		byte[] read = insert(startPos, endPos);

		return read;
	}

	private byte[] insert(int startPos, int endPos) throws IOException {
		byte[] b = new byte[1024];
		int len = 0;
		int index = 0;
		byte[] read = new byte[endPos-startPos+1];
		
		while((len=in.read(b))>0){
			if((index+1024)<=startPos){
				index+=1024;
				continue;
			}
			
			if(index<=startPos&&startPos<(index+1024)){
				
				if(endPos<(index+1024)){
					for(int i=0,j=startPos-index;i<read.length;i++,j++){
						read[i]=b[j];
					}
					index+=1024;
					break;
				}else{
				
					for(int i=0,j=startPos-index;i<(index+1024-startPos);i++,j++){
						read[i]=b[j];
					}
				}
				
			}
			
			if(startPos<index){
				
				if(endPos<(index+1024)){
					for(int i=0,j=index-startPos;i<(endPos-index+1);i++,j++){
						read[j] = b[i];
					}
					index+=1024;
					break;

				}
				
				for(int i=0,j=index;i<1024;i++,j++){
					read[j] = b[i];
				}
				
			}
			
			
			index+=1024;
		}
		return read;
	}

	public int getContentLength() {
		
		if(url==null||url.trim()==""){
			return 0;
		}
		
		try{
		
		URL u = new URL(url);
		in = u.openStream();
		if(in==null){
			return 0;
		}
		int length = in.available();
		return length;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void close() {
		

		try {
			if(in!=null){
				in.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
