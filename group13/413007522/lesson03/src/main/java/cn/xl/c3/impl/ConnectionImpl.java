package cn.xl.c3.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import cn.xl.c3.api.Connection;
import cn.xl.c3.api.ConnectionException;


class ConnectionImpl implements Connection{


	URL url;

	static final int BUFFER_SIZE = 1024;

	ConnectionImpl(String _url) throws ConnectionException{
		try {			
			url = new URL(_url);
		} catch (MalformedURLException e) {		

		}
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();

		httpConn.setRequestProperty("Range", "bytes=" + startPos + "-" 
				+ endPos);  	
		InputStream is = httpConn.getInputStream();


		/*byte[] b = new byte[endPos - startPos + 1];

		is.read(b, 0, endPos - startPos + 1);

		is.close();

		return b;*/

		byte[]  buff =  new byte[BUFFER_SIZE];

		int  totalLen = endPos - startPos + 1;

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		while(baos.size() < totalLen){
			int len = is.read(buff);
			if(len < 0){
				break;
			}
			baos.write(buff,0, len);  
		}

		if(baos.size() > totalLen){
			byte[] data = baos.toByteArray();
			return Arrays.copyOf(data, totalLen);
		}

		/*is.close();
		baos.close();
		httpConn.disconnect();*/
		return baos.toByteArray();
	}


	@Override
	public int getContentLength() {

		URLConnection con;
		try {
			con = url.openConnection();
			return con.getContentLength();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public InputStream getInputStream(int startPos, int endPos){
		
		HttpURLConnection httpConn = null;
		InputStream is = null;
		try {
			httpConn = (HttpURLConnection)url.openConnection();
			httpConn.setRequestProperty("Range", "bytes=" + startPos + "-" 
					+ endPos);  
			is = httpConn.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		//if(httpConn != null) httpConn.disconnect();
		
		return is;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
		
	}

}
