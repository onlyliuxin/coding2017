package week3.com.coding.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import week3.com.coding.download.api.Connection;
import week3.com.coding.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager
{
    /**
     * 给定一个url , 打开一个连接
     * 
     * @param url
     * @return
     */
    @Override
    public Connection open(String url)
    {
        Connection conn=null;
        URL httpUrl = null;
        HttpURLConnection urlConn = null;
        try
        {
            httpUrl = new URL(url);
            urlConn = (HttpURLConnection)httpUrl.openConnection();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        conn= new ConnectionImpl(urlConn);
        return conn;
    }
    
}
