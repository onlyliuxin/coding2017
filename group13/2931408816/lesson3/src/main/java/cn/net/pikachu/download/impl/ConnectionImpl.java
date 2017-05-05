package cn.net.pikachu.download.impl;

import cn.net.pikachu.download.LogUtil;
import cn.net.pikachu.download.api.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionImpl implements Connection {
    private String url;
    private InputStream is;

    public ConnectionImpl(String url) {
        this.url = url;
    }

    @Override
	public byte[] read(int startPos, int endPos) throws IOException {
        URL u = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        InputStream is = connection.getInputStream();
        int len = endPos-startPos+1;
        LogUtil.log(" startPos = "+startPos+", endPos = "+endPos+", len = "+len);
        byte[] bytes = new byte[is.available()>len?is.available():len];
        int totalReceived = 0;
        LogUtil.log("len = "+len+", available = "+is.available());
        int received = 0;
        try {
            int left = len - totalReceived;
            while (left > 0){
                LogUtil.log(" available = "+is.available());
                LogUtil.log(" left = " + left+", totalReceived = "+totalReceived+", len = "+len);
                if (left >= 1024){
                    received =  is.read(bytes,totalReceived,1024);
                }else {
                    received =  is.read(bytes,totalReceived,left);
                }
                totalReceived+=received;
                LogUtil.log(" received = "+received+", totalReceived = "+totalReceived);

                if (is.available() == 0){
                    LogUtil.log("is.available() == 0");
                    break;
                }
                if (received==0){
                    LogUtil.log("received == 0");
                    System.exit(0);
                }
                left = len - totalReceived;
                /*
                if (left == 0){
                    LogUtil.log("left = 0; break;");
                    break;
                }
                */
            }

        }catch (Exception e){
            e.printStackTrace();
            LogUtil.log("Exception received = "+received+", totalReceived = "+totalReceived);
            System.exit(0);
        }

		return bytes;
	}

	@Override
	public int getContentLength() {

        HttpURLConnection connection = null;
        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            return connection.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
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
