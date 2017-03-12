package com.coding2017.week3.download.impl;

import com.coding2017.week3.download.api.Connection;
import com.coding2017.week3.download.api.ConnectionException;
import com.coding2017.week3.download.api.ConnectionManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws ConnectionException {
        try {
            URL connUrl = new URL(url);
            URLConnection urlConnection = connUrl.openConnection();
            return new ConnectionImpl((HttpURLConnection) urlConnection);
        } catch (Exception e) {
            throw new ConnectionException(e);
        }
    }
}
