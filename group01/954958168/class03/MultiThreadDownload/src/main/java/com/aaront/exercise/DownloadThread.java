package com.aaront.exercise;

import com.aaront.exercise.api.Connection;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DownloadThread extends Thread {

    Connection conn;
    int startPos;
    int endPos;
    File file;

    public DownloadThread(Connection conn, int startPos, int endPos) {

        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        this.file = new File("hehe.jpg");
    }

    public void run() {
        try {
            byte[] content = conn.read(startPos, endPos);
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(startPos);
            raf.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}