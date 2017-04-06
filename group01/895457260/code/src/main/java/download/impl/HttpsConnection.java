package download.impl;

import download.api.ConnectionException;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;

/**
 * Created by Haochen on 2017/3/16.
 * TODO:
 */
public class HttpsConnection extends BaseConnection {

    HttpsConnection(String url, int startPos, int endPos) throws ConnectionException {
        super(url, startPos, endPos);
    }

    @Override
    protected void init(int startPos, int endPos) throws IOException {
        HttpsURLConnection connection = (HttpsURLConnection) super.connection;
        connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        openInputStream();
    }
}
