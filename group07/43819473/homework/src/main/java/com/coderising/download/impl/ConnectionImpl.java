package com.coderising.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
//import java.net.URLConnection;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection {

    public URL url = null;
    private static final int SIZE=1024;

    public ConnectionImpl(String urlStr){
        try {
            this.url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

//    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        URLConnection urlConnection=url.openConnection();
        urlConnection.setRequestProperty("Range", "bytes="+startPos+"-"+(endPos-startPos));
        InputStream inputStream=urlConnection.getInputStream();
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        byte[] buffer=new byte[SIZE];
        int len=-1;
        while((len=inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }

//    @Override
    public int getContentLength() {
        int length=-1;

        if(url!=null){
            URLConnection urlConnection= null;
            try {
                urlConnection = url.openConnection();
                length=urlConnection.getContentLength();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return length;
    }

//    @Override
    public void close() {


    }

}
