package com.coderising.download.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.apache.commons.lang3.StringUtils;

import com.coderising.download.api.Connection;
import com.google.common.base.Preconditions;

public class ConnectionImpl implements Connection {

    private HttpURLConnection conn;

    private String fileName;

    public Connection build(HttpURLConnection conn) {
        this.conn = conn;
        return this;
    }

    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        Preconditions.checkArgument(startPos < endPos);
        byte[] buffer = new byte[endPos - startPos];
        try (InputStream in = conn.getInputStream()) {
            in.read(buffer, 0, endPos - startPos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return buffer;
    }

    @Override
    public long getContentLength() {
        return conn.getContentLengthLong();
    }

    @Override
    public void close() {

    }

    @Override
    public String downLoadFileName() {
        if (StringUtils.isNotBlank(fileName)) {
            return fileName;
        }
        fileName = conn.getURL().getFile();
        return fileName;
    }
}
