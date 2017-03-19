package com.coderising.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coderising.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
    boolean downloadFinished = false;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDownload() {
        String url = "http://img-download.pchome.net/download/1k1/3b/49/ofuny2-1uit.jpg";
        FileDownloader downloader = new FileDownloader(url);
        ConnectionManager cm = new ConnectionManagerImpl();
        downloader.setConnectionManager(cm);

        downloader.setListener(new DownloadListener() {
            @Override
            public void notifyFinished() {
                downloadFinished = true;
            }
        });

        downloader.execute();

        // 等待多线程下载程序执行完毕
        while (!downloadFinished) {
            if(downloader.downloadFinish()){
                downloader.listener.notifyFinished();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("下载完成！");
    }

}
