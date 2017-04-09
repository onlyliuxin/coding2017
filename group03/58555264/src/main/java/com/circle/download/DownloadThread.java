package com.circle.download;

import com.circle.download.api.Connection;
import com.circle.download.api.ConnectionException;
import com.circle.download.api.DownloadListener;

import java.io.*;

/**
 * Created by keweiyang on 2017/3/10.
 */
public class DownloadThread extends Thread {

    private Connection conn;
    private int startPos;
    private int endPos;
    static int threadFinished = 0;

    private DownloadListener listener;
    private int threadNum;


    public DownloadThread(Connection conn, int startPos, int endPos, DownloadListener listener, int threadNum) {

        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        this.listener = listener;
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        try {
            this.conn.read(startPos, endPos);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } finally {
            synchronized (this) {
                threadFinished++;
                if (threadFinished == threadNum) {
                    listener.notifyFinished();
                }

            }

        }
        System.out.println("线程：" + Thread.currentThread().getId() + " , startPos:" + startPos + ",endPos:" + endPos);

    }
}

