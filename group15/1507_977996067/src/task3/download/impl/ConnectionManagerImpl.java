package task3.download.impl;


import task3.download.api.Connection;
import task3.download.api.ConnectionException;
import task3.download.api.ConnectionManager;

import java.io.FileInputStream;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws ConnectionException {
        ConnectionImpl connection = null;
        try {
            connection = new ConnectionImpl(new FileInputStream("f://pictures/b3.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
