package xyy.download.impl;


import vvv.download.api.ConnectionException;
import xyy.download.api.Connection;
import xyy.download.api.ConnectionManager;

import java.io.IOException;

/**
 * Created by 14258 on 2017/3/14.
 */
public class ConnectionManagerImpl implements ConnectionManager {
    private String url;

    @Override
    public Connection open(String url) throws ConnectionException, IOException {

        Connection conn = null;
        if (!url.equals(this.url)){
            conn = new ConnectionImpl(url);
            this.url = url;
        }else {
            conn = new ConnectionImpl(url, false);
        }



        return conn;
    }
}
