package download.impl;

import download.api.Connection;
import download.api.ConnectionException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Haochen on 2017/3/16.
 * TODO:
 */
public abstract class BaseConnection implements Connection {
    URLConnection connection;
    InputStream inputStream;

    private int contentLength;
    private int readLen;

    BaseConnection(String url, int startPos, int endPos) throws ConnectionException {
        contentLength = endPos - startPos + 1;
        try {
            connection = new URL(url).openConnection();
            init(startPos, endPos);
            inputStream.mark(contentLength);
        } catch (IOException e) {
            throw new ConnectionException();
        }
    }

    @Override
    public int read(byte[] buf) throws IOException {
        if (readLen >= contentLength) {
            return -1;
        }
        int n = inputStream.read(buf);
        if (readLen + n >= contentLength) {
            n =  contentLength - readLen;
            readLen = contentLength;
        } else {
            readLen += n;
        }
        return n;
    }

    protected abstract void init(int startPos, int endPos) throws IOException;

    @Override
    public int getContentLength() {
        return connection.getContentLength();
    }

    @Override
    public void reset() throws IOException {
        inputStream.reset();
        readLen = 0;
    }

    @Override
    public void close() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void openInputStream() throws IOException {
        inputStream = new BufferedInputStream(connection.getInputStream());
    }
}
