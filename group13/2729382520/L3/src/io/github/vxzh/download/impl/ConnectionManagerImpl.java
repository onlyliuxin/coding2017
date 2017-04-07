package io.github.vxzh.download.impl;

import io.github.vxzh.download.api.Connection;
import io.github.vxzh.download.api.ConnectionException;
import io.github.vxzh.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String path) throws ConnectionException {

        ConnectionImpl connection = null;
        try {
            connection = new ConnectionImpl(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}