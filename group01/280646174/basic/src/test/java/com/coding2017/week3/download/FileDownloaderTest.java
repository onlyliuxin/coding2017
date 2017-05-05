package com.coding2017.week3.download;

import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding2017.util.URLUtil;
import com.coding2017.week3.download.api.ConnectionManager;
import com.coding2017.week3.download.api.DownloadListener;
import com.coding2017.week3.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDownload() {

        int threadCount = 1;
        String url = "http://d.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=44efbe491e30e924cff194377c38423e/dcc451da81cb39dbf51ac417d1160924aa18309c.jpg";
        String downloadPath = "/tmp/";
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        String outFilePath = downloadPath + URLUtil.getFileNameFromURL(url);

        FileDownloader downloader = new FileDownloader(url, outFilePath, threadCount);
        ConnectionManager cm = new ConnectionManagerImpl();
        downloader.setConnectionManager(cm);

        downloader.setListener(new DownloadListener() {
            @Override
            public void notifyFinished() {
                countDownLatch.countDown();
            }

        });

        downloader.execute();

        try {
            countDownLatch.await();
            System.out.println("下载完成！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
