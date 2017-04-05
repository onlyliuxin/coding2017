package me.lzb.homework0326.download.impl;


import me.lzb.homework0326.download.api.Connection;
import me.lzb.homework0326.download.api.ConnectionException;
import me.lzb.homework0326.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {


    @Override
    public Connection open(String url) throws ConnectionException {
        return new ConnectionImpl(url);
    }


}
