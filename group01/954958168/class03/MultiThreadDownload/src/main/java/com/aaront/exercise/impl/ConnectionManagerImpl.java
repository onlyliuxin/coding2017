package com.aaront.exercise.impl;

import com.aaront.exercise.api.Connection;
import com.aaront.exercise.api.ConnectionException;
import com.aaront.exercise.api.ConnectionManager;

import java.io.IOException;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws ConnectionException {
        try {
            return new ConnectionImpl(url);
        } catch (IOException e) {
            throw new ConnectionException("创建连接失败");
        }
    }
}
