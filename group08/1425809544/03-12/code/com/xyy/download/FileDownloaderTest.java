package xyy.download;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xyy.download.api.ConnectionManager;
import xyy.download.api.DownloadListener;
import xyy.download.impl.ConnectionManagerImpl;

/**
 * Created by 14258 on 2017/3/14.
 */
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

        String url = "http://img.71lady.com/uploads/allimg/1701/2-1F11GKT4.jpg";
        FileDownloader fileDownloader = new FileDownloader(url);
        ConnectionManager connectionManager = new ConnectionManagerImpl();
        fileDownloader.setConnectionManager(connectionManager);
        fileDownloader.setListener(new DownloadListener() {
            @Override
            public void notifyFinished() {
                downloadFinished = true;
            }
        });

        fileDownloader.execute();

        while (!downloadFinished) {
            System.out.print("还没有下载完成，休眠五秒");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("下载完成！");


    }

}
