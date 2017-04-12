package com.dudy.learn01.download.impl;

import com.dudy.learn01.download.api.Connection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConnectionImpl implements Connection {


	private HttpURLConnection  connection;

	public ConnectionImpl(String url) {
		try {
			this.connection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
    public  byte[] read(int startPos, int endPos) throws IOException {

        connection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

        InputStream in = connection.getInputStream();
        byte buffer[] = new byte[endPos-startPos+1];
        byte result[] = new byte[endPos-startPos+1];
        int count = 0; // 记录已经读取的数据
        int length = -1 ;

        while ((length = in.read(buffer)) > 0){
           System.arraycopy(buffer,0,result,count,length);
           count += length;
        }
        return result;
	}

	@Override
	public int getContentLength() {
		return connection.getContentLength();
	}

	@Override
	public void close() {
		if (connection != null){
			connection.disconnect();
		}
	}

    public static void main(String[] args) throws  Exception{
	    //String PATH = "http://demo2.yun.myuclass.com/upload/demo2.yun.myuclass.com/winshare/pagelogo/250617391.png";
        String PATH = "http://www.lgstatic.com/www/static/mycenter/modules/common/img/tou_42952f6.png";

        URL url = new URL(PATH);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //conn.setConnectTimeout(5000);
        //conn.setRequestMethod("GET");
        //设置头部的参数，表示请求服务器资源的某一部分
        //conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        //设置了上面的头信息后，响应码为206代表请求资源成功，而不再是200
        int code = conn.getResponseCode();
        System.out.println(conn.getContentLength());
        if(code == 200){

            InputStream is = conn.getInputStream();
            int hasRead = 0;
            byte[] buf = new byte[conn.getContentLength()];
            System.out.println(buf.length);
            //这里要注意新创建一个RandomAccessFile对象，而不能重复使用download方法中创建的
            RandomAccessFile raf = new RandomAccessFile(new File("/Users/dudy/Desktop/1.png"), "rw");
            //将写文件的指针指向下载的起始点
            raf.seek(0);

            while((hasRead = is.read(buf,0,conn.getContentLength())) > 0) {
                System.out.println("hasRead = " + hasRead);
                raf.write(buf, 0, hasRead);
            }
            is.close();
            raf.close();
            conn.disconnect();
        }
    }

}