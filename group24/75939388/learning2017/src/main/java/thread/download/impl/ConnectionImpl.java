package thread.download.impl;

import thread.download.api.Connection;
import thread.download.api.ConnectionException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionImpl implements Connection {

    private int length = 0;

    private URL url;

    private HttpURLConnection conn;

    private InputStream is;

    private ByteArrayOutputStream baos;

    private ConnectionImpl() {}

    public ConnectionImpl(URL url) {
        this.url = url;
        try {
            this.conn = (HttpURLConnection) url.openConnection();
            this.conn.setRequestMethod("GET");
            this.conn.setReadTimeout(5000);
            int responseCode = this.conn.getResponseCode();
            System.out.println("连接状态=" + responseCode);
            if (responseCode != 200) {
                throw new ConnectionException("连接到" + url.toURI() + "失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] read(int startPos, int endPos) throws IOException {
        try {
            //设置读取段落
            this.conn = (HttpURLConnection) url.openConnection();
            this.conn.setRequestMethod("GET");
            this.conn.setReadTimeout(5000);
            this.conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
            //获取返回值
            int response = conn.getResponseCode();
            if(response != 200 && response != 206){
                throw new ConnectException("没有连接上" + url.toURI() + ", 状态码为" + response);
            }
            //开始读取
            int length = endPos - startPos + 1;
            this.is = conn.getInputStream();
            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream(length);
            while(-1 != is.read(buffer)){
                baos.write(buffer);
            }
            System.out.println(startPos + "-" + endPos + "文件段读取完成");
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            this.close();
        }
    }

    public int getContentLength() {
        try {
            this.length = this.conn.getContentLength();
            System.out.println("获取的文件长度=" + length);
            return this.length;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void close() {
        try {
            if(is != null){
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
