package task3.download.impl;

import task3.download.api.Connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionImpl implements Connection {
    private HttpURLConnection conn;

    public ConnectionImpl(String urlString) {
        URL targetUrl = null;
        try {
            targetUrl = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            conn = (HttpURLConnection) targetUrl.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        //设置读取的文件块
        conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream out =new ByteArrayOutputStream();
        int len=0;
        byte[] buff = new byte[1024];
        while ((len = is.read(buff)) != -1) {
            out.write(buff,0,len);
        }
        out.close();
        is.close();
        return out.toByteArray();
    }

    @Override
    public int getContentLength() {
        return conn.getContentLength();
    }

    @Override
    public void close() {
       conn.disconnect();
    }

}
