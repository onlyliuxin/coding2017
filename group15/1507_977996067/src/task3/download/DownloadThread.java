package task3.download;

import task3.download.api.Connection;

import java.io.File;
import java.io.FileOutputStream;
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
    }

    public DownloadThread(Connection conn, int startPos, int endPos, File file) {
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        this.file = file;
    }

    public void run() {
        RandomAccessFile randomAccessFile = null;
        try {
            byte[] read = conn.read(startPos, endPos);
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.skipBytes(startPos);
            randomAccessFile.write(read, 0, endPos - startPos + 1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile != null)
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
