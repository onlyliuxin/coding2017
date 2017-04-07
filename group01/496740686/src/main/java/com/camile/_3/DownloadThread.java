package com.camile._3;

import java.io.IOException;

import com.camile._3.api.Connection;

public class DownloadThread extends Thread{

    Connection conn;
    int startPos;
    int endPos;
    byte[] content;

    public DownloadThread(Connection conn, int startPos, int endPos) {

        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public void run() {
        try {
            content = conn.read(startPos, endPos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getContent() {
        return this.content;
    }
}
