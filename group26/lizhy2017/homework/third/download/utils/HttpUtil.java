package third.download.utils;

import java.io.IOException;
import java.net.HttpURLConnection;

import third.download.api.ConnectionException;

/**
 * ${}
 * Created by spark_lizhy on 2017/3/31.
 */

public class HttpUtil {
    /**
     * 检查连接状态
     */
    public static void checkStatus(HttpURLConnection connection) throws IOException, ConnectionException {
        int responseCode = connection.getResponseCode();
        System.out.println("server response code: " + responseCode);
        if (responseCode != HttpURLConnection.HTTP_OK && responseCode != HttpURLConnection.HTTP_PARTIAL) {
            throw new ConnectionException("server response code: " + responseCode);
        }
    }
}
