package week3.thread;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

public class FileDownload {

    private String address;

    public FileDownload(String address) {
        this.address = address;
    }

    public void download(int threadCount) {
        try {
            URL url = new URL(address);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url
                    .openConnection();
            int length = httpURLConnection.getContentLength();
            System.out.println("文件大小："+length);
            File file = new File("D:\\download.jpg");
            CountDownLatch countDownLatch = new CountDownLatch(threadCount);
            // 计算每个线程下载的数据大小
            int blockSize = length / threadCount;
            for (int i = 0; i < threadCount; i++) {
                int startPos = blockSize * i;
                int endPos = blockSize * (i + 1);
                if (i == threadCount - 1) {
                    //最后一个下载剩下的
                    endPos = length;
                }
                new DownloadThread(file, countDownLatch, address, startPos,
                        endPos - 1).start();
            }
            while (countDownLatch.getCount() != 0) {
                System.out.println("下载中....");
                try {
                    // 休眠
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}