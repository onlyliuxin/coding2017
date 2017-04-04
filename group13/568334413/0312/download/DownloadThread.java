package download;


import download.api.Connection;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

public class DownloadThread extends Thread {

    private CountDownLatch countDownLatch;
    Connection conn;
    int startPos;
    int endPos;

    public DownloadThread(Connection conn, int startPos, int endPos, CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public void run() {
        try {
            byte[] bytes = conn.read(startPos, endPos);
            if (bytes == null) {
                return;
            }
            File file = new File("./src/main/resources/1.mp4");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0);
            randomAccessFile.write(bytes);
            randomAccessFile.close();
            countDownLatch.countDown();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
