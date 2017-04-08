import api.Connection;

import java.io.RandomAccessFile;
import java.util.concurrent.CyclicBarrier;

public class DownloadThread extends Thread {

    Connection conn;
    int startPos;
    int endPos;

    private CyclicBarrier barrier;

    public DownloadThread(Connection conn, int startPos, int endPos, CyclicBarrier barrier) {

        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        this.barrier = barrier;
    }

    public void run() {
        try {
            byte[] buffer = conn.read(startPos, endPos);
            RandomAccessFile raf = new RandomAccessFile("/Users/nvarchar/example.jpg", "rw");
            raf.seek(startPos);
            raf.write(buffer);
            raf.close();
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
