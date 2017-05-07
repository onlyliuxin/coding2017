package com.bruce.homework0312.mydownload;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Bruce.Jiao on 2017/3/11.
 */
public class DownloadFileMultiThread {
    private String path;
    private int threadCount;

    public DownloadFileMultiThread(String path, int threadCount){
        this.path = path;
        this.threadCount = threadCount;
    }

    public void download() throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(6000);
        conn.setRequestMethod("GET");
        //从服务器请求全部资源返回200，请求部分资源返回206
        int code = conn.getResponseCode();
        if(code == 200) {
            //返回的文件的长度
            int length = conn.getContentLength();
            //在客户端本地创建一个大小跟服务器端文件大小一样的本地临时文件
            RandomAccessFile raf = new RandomAccessFile("log4j.jar","rw");
            raf.setLength(length);
            raf.close();
            int blockSize = length/threadCount;
            for(int threadId = 1; threadId < threadCount; threadId++) {
                int startIndex = (threadId - 1)*blockSize;
                int endIndex = threadId*blockSize - 1;
                if(threadId == threadCount) {
                    endIndex = length;
                }
                new DownloadThread(path, threadId, startIndex, endIndex);
            }
        }
    }

    public static class DownloadThread extends Thread {
        private String path;
        private int threadId;
        private int startIndex;
        private int endIndex;

        public DownloadThread(String path, int threadId, int startIndex, int endIndex) {
            super();
            this.path = path;
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(6000);
                conn.setRequestMethod("GET");
                //请求服务器下载部分文件，指定文件的位置
                conn.setRequestProperty("Range", "bytes=" + startIndex + "-" + endIndex);
                int code = conn.getResponseCode();
                //已经设置了请求的位置，返回的是当前位置对应的文件的输入流
                InputStream is = conn.getInputStream();
                RandomAccessFile raf = new RandomAccessFile("log4j.jar", "rw");
                //定位文件
                raf.seek(startIndex);
                int len = 0;
                byte[] buffer = new byte[1024];
                while((len = is.read(buffer)) != -1) {
                    raf.write(buffer, 0, len);
                }
                is.close();
                raf.close();
                System.out.println("线程：" + threadId + "下载完毕");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
