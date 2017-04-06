package com.johnChnia.coderising2017.download.impl;

import com.johnChnia.coderising2017.download.api.Connection;
import com.johnChnia.coderising2017.download.api.ConnectionException;
import com.johnChnia.coderising2017.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws ConnectionException {
        return new ConnectionImpl(url);
    }

}
