package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws ConnectionException {
        ConnectionImpl conn = new ConnectionImpl();
        try {
            URL fileUrl = new URL(url);
            HttpURLConnection connection = ((HttpURLConnection) fileUrl.openConnection());
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5 * 1000);
            conn.setConn(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
