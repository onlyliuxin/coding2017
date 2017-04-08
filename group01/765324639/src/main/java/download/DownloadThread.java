package download;

import java.io.IOException;
import java.io.RandomAccessFile;

import download.api.Connection;

public class DownloadThread extends Thread {

    Connection conn;
    int startPos;
    int endPos;


    public DownloadThread(Connection conn, int startPos, int endPos) {

        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;

    }

    @Override
    public void run() {
        System.out.println("start download:" + startPos + "~" + endPos);
        byte[] data = new byte[endPos - startPos];
        try {
            data = conn.read(startPos, endPos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeToFile(data);
    }
    
    private void writeToFile(byte[] data) {
        RandomAccessFile file;
        try {
            file = new RandomAccessFile("download20170311.jpg", "rw");
            file.seek(startPos);
            file.write(data, 0, data.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
