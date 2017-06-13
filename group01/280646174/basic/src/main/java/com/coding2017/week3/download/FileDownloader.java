package com.coding2017.week3.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.coding2017.week3.download.api.Connection;
import com.coding2017.week3.download.api.ConnectionException;
import com.coding2017.week3.download.api.ConnectionManager;
import com.coding2017.week3.download.api.DownloadListener;

public class FileDownloader {

    private String url;

    private String outFilePath;

    private DownloadListener listener;

    private ConnectionManager cm;

    private int threadCount;

    public FileDownloader(String _url, String outFilePath, int threadCount) {
        this.url = _url;
        this.outFilePath = outFilePath;
        this.threadCount = threadCount;
    }

    public void execute() {
        // 在这里实现你的代码， 注意： 需要用多线程实现下载
        // 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
        // (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
        // (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
        // 线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
        // 具体的实现思路：
        // 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
        // 2. 至少启动3个线程下载， 注意每个线程需要先调用ConnectionManager的open方法
        // 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
        // 3. 把byte数组写入到文件中
        // 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法

        try {
            int contentLength = getUrlContentLength(url);
            FileOutputStream fileOutputStream = new FileOutputStream(outFilePath);
            fileOutputStream.write(new byte[contentLength]);
            fileOutputStream.flush();
            fileOutputStream.close();

            for (int i = 0; i < threadCount; i++) {
                downloadPart(i, contentLength);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadPart(int part, int length) {
        Connection conn;
        try {
            conn = cm.open(this.url);
            int startPos = part * length / threadCount;
            int endPos = Math.min(length - 1, (part + 1) * length / threadCount);
            System.out.println("文件总长度: " + length);
            new DownloadThread(conn, startPos, endPos, outFilePath, listener).start();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
    }

    private int getUrlContentLength(String url) throws IOException {
        URL connUrl = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) connUrl.openConnection();
        int length = urlConnection.getContentLength();
        urlConnection.disconnect();
        return length;
    }

    public void setListener(DownloadListener listener) {
        this.listener = listener;
    }

    public void setConnectionManager(ConnectionManager ucm) {
        this.cm = ucm;
    }

    public DownloadListener getListener() {
        return this.listener;
    }

}
