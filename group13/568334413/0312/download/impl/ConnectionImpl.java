package download.impl;

import download.api.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


public class ConnectionImpl implements Connection {

    private static int RESPONSECODE = 200;
    private String urlAddress;
    private InputStream inputStream;
    private HttpURLConnection httpURLConnection = null;
    private URL url = null;

    public ConnectionImpl(String urlAddress) {
        this.urlAddress = urlAddress;
        try {
            url = new URL(urlAddress);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] read(int startPos, int endPos) {

        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setConnectTimeout(10000);
        byte[] tempByteArray = new byte[0];
        try {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            tempByteArray = new byte[httpURLConnection.getContentLength()];
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            int index = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                System.arraycopy(buffer, 0, tempByteArray, index, len);
                index = index + len;
            }
            inputStream.close();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempByteArray;
    }

    public int getContentLength() throws IOException {
        int code = httpURLConnection.getResponseCode();
        if (code == RESPONSECODE) {
            inputStream = httpURLConnection.getInputStream();
            return httpURLConnection.getContentLength();
        }
        return 0;
    }

    public void close() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
