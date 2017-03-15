package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.net.URLConnection;
import java.util.Arrays;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private InputStream stream ;
	private URL url;

	public ConnectionImpl(String urlString){
		try{ 
	        this.url = new URL(urlString);  
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public byte[] read(long startPos, long endPos) throws IOException {
		this.stream = this.url.openStream(); 
		stream.skip(startPos);
		int maxLength = (int)(endPos-startPos);
        byte[] buff = new byte[maxLength];   
        int hasRead = 0; 
        int offset = 0;//��ȡ��ƫ����
        while(true) {
            hasRead = stream.read(buff,offset,maxLength-offset);  
            if(hasRead <= 0){
            	break;
            }
            offset += hasRead;
        }
		return buff;
	}
	
	private byte[] concat(byte[] a, byte[] b) {  
		byte[] c= new byte[a.length+b.length];  
		System.arraycopy(a, 0, c, 0, a.length);  
		System.arraycopy(b, 0, c, a.length, b.length);  
		return c;  
	}  

	@Override
	public long getContentLength() { 
        // �򿪸�URL��Ӧ��URLConnection  
        URLConnection con;
		try {
			con = url.openConnection();			
	        // ��ȡ����URL��Դ�ĳ���  
	        return con.getContentLength(); 
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
		return 0;
	}

	@Override
	public void close() {
		try {  
            if (this.stream != null) {  
            	this.stream.close();  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
	}
}
