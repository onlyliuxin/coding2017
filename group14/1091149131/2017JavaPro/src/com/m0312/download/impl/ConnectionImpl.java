package com.m0312.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.m0312.download.api.Connection;

public class ConnectionImpl implements Connection{
	URLConnection urlCon;
	URL url;
	static final int BUFFER_SIZE = 1024;
	ConnectionImpl(String _url){
		try {
			url=new URL(_url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		//只能写出第一部分
		byte[] buffer=new byte[endPos-startPos+1];
		HttpURLConnection urlCon2 = (HttpURLConnection)url.openConnection();
		urlCon2.setRequestProperty("Range", "bytes=" + startPos + "-"
                + endPos);
		InputStream is=urlCon2.getInputStream();
		//is.skip(startPos);
		is.read(buffer, 0, endPos-startPos+1);//因为没有+1，一直是只有三分之一部分
		is.close();
		return buffer;
		
		/*HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
		
		httpConn.setRequestProperty("Range", "bytes=" + startPos + "-"
                + endPos);
		
		InputStream is  = httpConn.getInputStream();
		
		//is.skip(startPos);		
		
        byte[] buff = new byte[BUFFER_SIZE];  
     
        int totalLen = endPos - startPos + 1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();        
        
        while(baos.size() < totalLen){  
        	
        	int len = is.read(buff);           
            if (len < 0) {  
                break;  
            }             
            baos.write(buff,0, len);  
            System.out.println("is read length: "+len);
            System.out.println("baos.size: "+baos.size());
        }  
        if(baos.size() > totalLen){
        	byte[] data = baos.toByteArray();
        	return Arrays.copyOf(data, totalLen);
        }
		return baos.toByteArray();
		*/
		/**
		 * 开始读[0,1603]
			开始读[1604,3207]
			is read length: 1024
			is read length: 1024
			baos.size: 1024
			baos.size: 1024
			开始读[3208,4811]
			is read length: 580
			baos.size: 1604    ///size会累积，等于度过的所有buffer size
			is read length: 1024
			baos.size: 1024
			is read length: 580
			baos.size: 1604
			is read length: 580
			baos.size: 1604
		 */
	}

	@Override
	public int getContentLength() {
		return urlCon.getContentLength();
	}

	@Override
	public void close() {
		if(urlCon!=null){
			//???
		}
	}
	@Override
	public URLConnection getUrlCon() {
		return urlCon;
	}
	@Override
	public void setUrlCon(URLConnection urlCon) {
		this.urlCon = urlCon;
	}

}
