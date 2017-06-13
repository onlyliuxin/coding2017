package main.coding_170309.impl;

import main.coding_170309.api.Connection;
import main.coding_170309.api.ConnectionException;
import main.coding_170309.api.ConnectionManager;

/**
 * Created by peter on 2017/3/9.
 */
public class ConnectionManagerImpl implements ConnectionManager {
    @Override
    public Connection open(String url) throws ConnectionException {
        Connection conn = new ConnectionImpl(url);
        return conn;
    }
}
