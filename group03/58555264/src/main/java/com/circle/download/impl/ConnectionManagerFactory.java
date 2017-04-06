package com.circle.download.impl;

import com.circle.download.api.Connection;
import com.circle.download.api.ConnectionManager;

import java.io.File;

/**
 * Created by keweiyang on 2017/3/11.
 */
public class ConnectionManagerFactory {

    private static volatile ConnectionManager manager = null;

    private ConnectionManagerFactory() {

    }

    public static ConnectionManager getManager(File file) {

        if (manager == null) {
            synchronized (ConnectionManagerFactory.class) {
                if (manager == null) {
                    manager = new ConnectionManagerImpl(file);
                }
            }
        }

        return manager;
    }
}
