package com.aaront.exercise.impl;

import com.aaront.exercise.api.Connection;
import com.aaront.exercise.api.ConnectionException;
import com.aaront.exercise.api.ConnectionManager;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String url) throws ConnectionException {
        try {
            return new ConnectionImpl(parse(url));
        } catch (FileNotFoundException e) {
            throw new ConnectionException("创建连接失败");
        }
    }

    private String parse(String url) {
        String pattern = "(http|https)://[a-zA-Z0-9]+:[0-9]+/([a-zA-Z0-9.]+)";
        Matcher compile = Pattern.compile(pattern).matcher(url);
        if(!compile.matches()) throw new ConnectionException("资源url不合法");
        return compile.group(2);
    }
}
