package com.coderising.download.impl;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.coderising.download.api.Connection;


public class ConnectionImpl implements Connection{
	URL url;

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		URLConnection connection=url.openConnection();
        InputStream ins =connection.getInputStream();
        byte[] buffer = new byte[endPos-startPos+1];
//        ins.skip(startPos);
//        ins.read(buffer,0, endPos-startPos);
        int at = startPos;
        long  amt=0;
        while(at > 0) {  
             amt = ins.skip(at-amt);  
            if (amt<=0) {  
                break; 
            }  
            at -= amt;  
        }  
        int curPos =0;
        while(true){  
        	int readByte  = ins.read(buffer,curPos, endPos-startPos-curPos);  
            if(readByte <=0){  
                break;  
            }            
            curPos= readByte + curPos;  
        }         
        ins.close();	
		return buffer;
	}

	@Override
	public int getContentLength() {
		
		try {
			return url.openConnection().getContentLength();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public void close() {
		
		
	}

}
