package week3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zndbl on 2017/3/27.
 */
public class DownloadThread extends Thread {

    private File file;
    private CountDownLatch countDownLatch;
    private String address;
    private int startPos;
    private int endPos;

    public DownloadThread(File file, CountDownLatch countDownLatch, String address, int startPos, int endPos ) {
        this.file = file;
        this.countDownLatch = countDownLatch;
        this.address = address;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    public void run() {
        Thread current = Thread.currentThread();
        System.out.println(current.getName() + "开始下载" + startPos + "_" + endPos);
        RandomAccessFile randomAccessFile = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(address);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Range", "bytes=" + startPos + "_" + endPos);
            inputStream = httpURLConnection.getInputStream();
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(startPos);
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(bytes)) != -1) {
                randomAccessFile.write(bytes, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                randomAccessFile.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(current.getName() + "下载完成");
            countDownLatch.countDown();
        }

    }


}
