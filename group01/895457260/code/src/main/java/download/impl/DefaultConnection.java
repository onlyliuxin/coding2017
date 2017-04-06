package download.impl;

import download.api.ConnectionException;

import java.io.IOException;

/**
 * Created by Haochen on 2017/3/16.
 * TODO:
 */
class DefaultConnection extends BaseConnection {

    DefaultConnection(String url, int startPos, int endPos) throws ConnectionException {
        super(url, startPos, endPos);
    }

    @Override
    protected void init(int startPos, int endPos) throws IOException {
        openInputStream();
        skipBytes(startPos);
    }

    private void skipBytes(long n) throws IOException {
        while (n > 0) {
            n -= inputStream.skip(n);
        }
    }
}
