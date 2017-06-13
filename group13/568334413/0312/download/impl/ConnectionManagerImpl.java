package download.impl;


import download.api.Connection;
import download.api.ConnectionException;
import download.api.ConnectionManager;

import java.io.IOException;

public class ConnectionManagerImpl implements ConnectionManager {


    public Connection open(String address) throws ConnectionException, IOException {

        Connection connection = new ConnectionImpl(address);

        return connection;
    }

}
