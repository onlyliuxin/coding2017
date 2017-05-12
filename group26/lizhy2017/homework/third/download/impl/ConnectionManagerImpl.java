package third.download.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import third.download.api.Connection;
import third.download.api.ConnectionException;
import third.download.api.ConnectionManager;
import third.download.utils.HttpUtil;

/**
 * 连接管理器
 */
public class ConnectionManagerImpl implements ConnectionManager {

    /**
     * 给定一个url , 打开一个连接
     */
    @Override
    public Connection open(String url) throws ConnectionException {
        HttpURLConnection connection = getConnection(url);
        return new ConnectionImpl(connection);
    }

    @Override
    public int getContentLength(String urlStr) throws ConnectionException {
        HttpURLConnection connection=getConnection(urlStr);
        int length=0;
        try {
            HttpUtil.checkStatus(connection);
            length=connection.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
        return length;
    }

    private HttpURLConnection getConnection(String _url) throws ConnectionException {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(_url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            return connection;
        } catch (IOException e) {
            new ConnectionException(e);
        return null;
        }
    }
}
