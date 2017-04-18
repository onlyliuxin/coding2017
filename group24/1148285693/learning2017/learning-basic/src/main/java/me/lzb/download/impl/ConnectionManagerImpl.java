package me.lzb.download.impl;


import me.lzb.download.api.Connection;
import me.lzb.download.api.ConnectionException;
import me.lzb.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {


    @Override
    public Connection open(String url) throws ConnectionException {
        return new ConnectionImpl(url);
    }


}
