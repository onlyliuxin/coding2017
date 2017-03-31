package cn.net.pikachu.download.impl;

import cn.net.pikachu.download.api.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionImpl implements Connection {
    private URL url;
    private InputStream is;
    private int totalReceived = 0;
    public ConnectionImpl(URL url) {
        this.url = url;
    }

    @Override
	public byte[] read(int startPos, int endPos) throws IOException {
        System.out.println("startPos = "+startPos+", endPos = "+endPos);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        is = connection.getInputStream();
        int len = endPos-startPos;
        byte[] bytes = new byte[len];
        while (is.available()>0){
            System.out.println("available = "+is.available());
            int left = len - totalReceived;
            System.out.println("len - totalReceived = " + left);
            int received;
            if (left >= 1024){
                received =  is.read(bytes,totalReceived,1024);
            }else {
                received =  is.read(bytes,totalReceived,left);
            }
            System.out.println("received = "+received);
            totalReceived+=received;
            if (received == 0){
                break;
            }
        }
		return bytes;
	}

	@Override
	public int getContentLength() {

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            return connection.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return -1;
	}

	@Override
	public void close() {
        if (is!=null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

}
