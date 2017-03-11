package com.coderising.download.impl;

import java.net.HttpURLConnection;
import java.net.URL;

import com.coderising.download.api.ConnectionException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionManager;
import com.google.common.base.Preconditions;

public class ConnectionManagerImpl implements ConnectionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManagerImpl.class);

    @Override
    public Connection open(String url) throws ConnectionException {
        Preconditions.checkArgument(StringUtils.isNoneBlank(url), "非法链接");
        Connection connection = new ConnectionImpl();
        try {
            URL link = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) link.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            connection.build(conn);
        } catch (Exception e) {
            LOGGER.error("连接打开失败");
        }
        return connection;
    }

    @Override
    public Connection open(String url, int start, int end) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(url), "非法链接");
        Connection connection = new ConnectionImpl();
        try {
            URL link = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) link.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            System.out.println("bytes=" + start + "-" + end);
            conn.setRequestProperty("Range", "bytes=" + start + "-" + end);
            connection.build(conn);
        } catch (Exception e) {
            LOGGER.error("链接打开失败");
        }
        return connection;
    }

}
