package com.johnChnia.coderising2017.download;

import com.johnChnia.coderising2017.download.api.ConnectionManager;
import com.johnChnia.coderising2017.download.api.DownloadListener;
import com.johnChnia.coderising2017.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

public class FileDownloaderTest {
    boolean downloadFinished = false;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDownload() throws FileNotFoundException {

        String url = "http://download.ydstatic.com/notewebsite/downloads/YNote.exe";


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
            try {
                System.out.println("还没有下载完成，休眠五秒");
                //休眠5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("下载完成！");



    }

}
