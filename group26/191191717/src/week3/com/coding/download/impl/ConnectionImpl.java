package week3.com.coding.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import week3.com.coding.download.api.Connection;

public class ConnectionImpl implements Connection
{
    HttpURLConnection conn;
    
    public ConnectionImpl()
    {
    }
    
    public ConnectionImpl(HttpURLConnection urlConn)
    {
        this.conn = urlConn;
    }
    
    public HttpURLConnection getConn()
    {
        return conn;
    }
    
    public void setConn(HttpURLConnection conn)
    {
        this.conn = conn;
    }
    
    @Override
    public byte[] read(int startPos, int endPos)
        throws IOException
    {
        System.out.println("startPos: " + startPos + " endPos " + endPos);
        conn.setRequestProperty("Range", "bytes=" + startPos + "-" + (endPos + 1));
        InputStream is = conn.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = is.read(buff)) != -1)
        {
            out.write(buff, 0, len);
        }
        byte[] bs = out.toByteArray();
        return bs;
    }
    
    /**
     * 获取即将下载文件的长度
     */
    @Override
    public int getContentLength()
    {
        
        return conn == null ? 0 : conn.getContentLength();
    }
    
    @Override
    public void close()
    {
        if (conn != null)
        {
            conn.disconnect();
        }
    }
    
}
