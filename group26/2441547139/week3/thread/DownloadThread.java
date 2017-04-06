package week3.thread;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

public class DownloadThread extends Thread {

    private File file;
    private CountDownLatch countDownLatch;
    private String address;
    private int startPos;
    private int endPos;

    public DownloadThread(File file, CountDownLatch countDownLatch,
                          String address, int startPos, int endPos) {
        super();
        this.file = file;
        this.countDownLatch = countDownLatch;
        this.address = address;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public void run() {
        Thread current = Thread.currentThread();
        System.out.println(current.getName() + "开始下载:" + startPos + "-"
                + endPos);
        RandomAccessFile randomAccessFile = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(address);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url
                    .openConnection();
            httpURLConnection.setRequestProperty("Range", "bytes=" + startPos
                    + "-" + endPos);
            inputStream = httpURLConnection.getInputStream();
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(startPos);
            byte[] bytes = new byte[1024];
            int read = 0;
            while ((read = inputStream.read(bytes)) != -1) {
                randomAccessFile.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(current.getName() + "下载完成");
            countDownLatch.countDown();
        }

    }
}