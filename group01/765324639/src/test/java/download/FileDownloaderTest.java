package download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import download.api.ConnectionManager;
import download.api.DownloadListener;
import download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
    boolean downloadFinished = false;

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testDownload() {

//        String url = "http://121.42.185.101/forum/test.jpg"; // 此图片较大
        String url = "http://121.42.185.101/forum/weixin.jpg"; // 此图片较小

        FileDownloader downloader = new FileDownloader(url);


        ConnectionManager cm = new ConnectionManagerImpl();
        downloader.setConnectionManager(cm);

        downloader.setListener(new DownloadListener() {
            @Override
            public void notifyFinished() {
                downloadFinished = true;
            }

        });

        new Thread() {
            @Override
            public void run() {
                downloader.execute();
            }
        }.start();

        // 等待多线程下载程序执行完毕
        while (!downloadFinished) {
            try {
                System.out.println("Downloaded not complete, sleep 5 second");
                // 休眠5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("download complete！");

    }

}
