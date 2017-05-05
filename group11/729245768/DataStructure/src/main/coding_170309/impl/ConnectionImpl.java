package main.coding_170309.impl;

import main.coding_170309.api.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by peter on 2017/3/9.
 */
public class ConnectionImpl implements Connection {
    private String url;
    private HttpURLConnection urlConnection;

    public ConnectionImpl(String url) {
        this.url = url;
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        urlConnection = (HttpURLConnection) new URL(url).openConnection();
        urlConnection.setRequestProperty("Range","bytes="+startPos+"-"+endPos);//从指定位置开始
        InputStream in = urlConnection.getInputStream();
        byte[] data = new byte[endPos-startPos+1];
        byte[] temp = new byte[1024];
        int pointer=0;//表示data每次偏移量
        int length ;//表示一次能读取的bit数
        while ((length=in.read(temp,0,temp.length))!=-1){
            System.arraycopy(temp,0,data,pointer,length);
            pointer+=length;
        }
        return Arrays.copyOf(data,pointer);
    }

    @Override
    public int getContentLength() {
        return urlConnection.getContentLength();
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public void close() {
        urlConnection.disconnect();
    }
}
