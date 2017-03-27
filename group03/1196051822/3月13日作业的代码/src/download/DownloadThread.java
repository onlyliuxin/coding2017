package com.coderising.download;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;

import java.io.*;
import java.util.concurrent.locks.Lock;

public class DownloadThread extends Thread {

    ConnectionManager cm;
    int startPos;
    int endPos;
    RandomAccessFile raf;
    String url;
    DownloadListener listener;

    public DownloadThread(ConnectionManager cm, int startPos, int endPos, String url, DownloadListener downloadListener) {
        this.cm = cm;
        this.startPos = startPos;
        this.endPos = endPos;
        this.url = url;
        this.listener = downloadListener;
    }

    public void run() {
        try {
            Connection conn = cm.open(url);
            byte[] bytes = conn.read(startPos, endPos);
            raf = new RandomAccessFile(new File("/Users/byhieg/Desktop/2.png"), "rwd");
            System.out.println(bytes.length);
            raf.seek(startPos);
            raf.write(bytes);
            if (raf.length() >= (conn.getContentLength() - 2)) {
                listener.notifyFinished();
            }

        } catch (IOException | ConnectionException e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}
