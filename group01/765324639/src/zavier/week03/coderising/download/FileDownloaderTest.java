package zavier.week03.coderising.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import zavier.week03.coderising.download.api.ConnectionManager;
import zavier.week03.coderising.download.api.DownloadListener;
import zavier.week03.coderising.download.impl.ConnectionManagerImpl;

public class FileDownloaderTest {
    boolean downloadFinished = false;

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    @Test
    public void testDownload() {

        String url = "http://121.42.185.101/forum/test.jpg";

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
                System.out.println("还没有下载完成，休眠五秒");
                // 休眠5秒
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("下载完成！");



    }

}
