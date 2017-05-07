package impl;

import api.Connection;
import api.ConnectionException;
import api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws ConnectionException {
        Connection connection = new ConnectionImpl(url);
        return connection;
    }

}
