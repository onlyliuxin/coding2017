package zavier.week03.coderising.download.impl;

import zavier.week03.coderising.download.api.Connection;
import zavier.week03.coderising.download.api.ConnectionException;
import zavier.week03.coderising.download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws ConnectionException {
        return new ConnectionImpl(url);
    }

}
