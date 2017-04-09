package com.circle.download.impl;

import com.circle.download.api.Connection;
import com.circle.download.api.ConnectionException;
import com.circle.download.api.ConnectionManager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by keweiyang on 2017/3/10.
 */
class ConnectionManagerImpl implements ConnectionManager {
    private File file;

    public ConnectionManagerImpl(File file) {

        this.file = file;

    }

    @Override
    public Connection open(String url) throws ConnectionException, IOException {
        return new ConnectionImpl(url, this.file);
    }


}
