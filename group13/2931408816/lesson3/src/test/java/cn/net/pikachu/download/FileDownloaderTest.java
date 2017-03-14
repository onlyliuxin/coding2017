package cn.net.pikachu.download;

import cn.net.pikachu.download.api.ConnectionManager;
import cn.net.pikachu.download.api.DownloadListener;
import cn.net.pikachu.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


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

//        String url = "http://localhost:8080/test.jpg";

        String url = "http://n1.itc.cn/img8/wb/recom/2016/07/26/146946506808699302.JPEG";
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
                System.out.println("None");
                //休眠5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("下载完成！");

        System.out.println("Done!");


    }

}
