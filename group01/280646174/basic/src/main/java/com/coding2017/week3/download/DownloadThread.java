package com.coding2017.week3.download;

import com.coding2017.week3.download.api.Connection;
import com.coding2017.week3.download.api.DownloadListener;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread {

    private static final String READ_WRITE_MODE = "rw";

    private Connection conn;
    private int startPos;
    private int endPos;
    private String fileName;
    private DownloadListener listener;

    public DownloadThread(Connection conn, int startPos, int endPos, String fileName, DownloadListener listener) {
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        this.fileName = fileName;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始下载: " + startPos + "到" + endPos);
            byte[] read = conn.read(startPos, endPos);
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, READ_WRITE_MODE);
            randomAccessFile.seek(startPos);
            randomAccessFile.write(read);
            listener.notifyFinished();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
