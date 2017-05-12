package coding.coderising.download;

import coding.coderising.download.api.ConnectionManager;
import coding.coderising.download.api.DownloadListener;
import coding.coderising.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jiaxun
 */
public class TestDownload {

    private static boolean downloaderFinished = false;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testDownload() {
        String downloadUrl = "http://img.ithome.com/newsuploadfiles/2017/3/20170324_152202_144.jpg";
        String savePath = "/Users/jiaxun/Downloads/download_thread.jpg";

        FileDownloader downloader = new FileDownloader(downloadUrl, savePath);

        ConnectionManager connectionManager = new ConnectionManagerImpl();
        downloader.setConnectionManager(connectionManager);

        downloader.setDownloadListener(new DownloadListener() {
            @Override
            public void notifyFinished() {
                downloaderFinished = true;
            }
        });

        downloader.execute();

        while (!downloaderFinished) {
            try {
                System.out.println("还没有下载完成，休眠五秒");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("下载完成！");
    }

}
