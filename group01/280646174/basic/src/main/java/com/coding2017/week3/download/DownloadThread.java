package com.coding2017.week3.download;

import com.coding2017.week3.download.api.Connection;

public class DownloadThread extends Thread {

    private Connection conn;
    private int startPos;
    private int endPos;

    public DownloadThread(Connection conn, int startPos, int endPos) {
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public void run() {

    }
}
