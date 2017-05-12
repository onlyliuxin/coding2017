package coding.coderising.download.impl;

import coding.coderising.download.api.Connection;
import coding.coderising.download.api.ConnectionException;
import coding.coderising.download.api.ConnectionManager;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author jiaxun
 */
public class ConnectionManagerImpl implements ConnectionManager {

    @Override
    public Connection open(String urlString) throws ConnectionException {

        try {
            URL url = new URL(urlString);
            return new ConnectionImpl((HttpURLConnection) url.openConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
