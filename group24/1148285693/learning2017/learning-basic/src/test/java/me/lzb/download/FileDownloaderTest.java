package me.lzb.download;

import me.lzb.download.api.ConnectionManager;
import me.lzb.download.api.DownloadListener;
import me.lzb.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileDownloaderTest {

    private static final String imageUrl = "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-499994.png";

    boolean downloadFinished = false;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDownload() {


        FileDownloader downloader = new FileDownloader(imageUrl, "D:\\code\\learning\\tmp\\test.jpg");


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
