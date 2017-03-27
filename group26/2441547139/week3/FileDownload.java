package week3;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zndbl on 2017/3/27.
 */
public class FileDownload {

    private String address;

    public FileDownload(String address) {
        this.address = address;
    }

    public void download(int threadCount) {
        try {
            URL url = new URL(address);
            HttpURLConnection HttpurlConnection = (HttpURLConnection) url.openConnection();
            int length = HttpurlConnection.getContentLength();
            System.out.println("文件大小"+length);
            File file = new File("D:\\download.jpg");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.setLength(length);
            randomAccessFile.close();
            CountDownLatch countDownLatch = new CountDownLatch(threadCount);
            int blockSize = length / threadCount;
            for (int i = 0; i < threadCount ; i++) {
                int startPos = blockSize * i;
                int endPos = blockSize * (i + 1);
                if(i == threadCount - 1) {
                    endPos = length;
                }
                new DownloadThread(file, countDownLatch, address, startPos, endPos - 1).start();
            }
            while (countDownLatch.getCount() != 0) {
                System.out.println("下载中");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("下载完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
