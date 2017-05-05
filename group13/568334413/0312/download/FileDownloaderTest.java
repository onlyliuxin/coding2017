package download;


import download.api.ConnectionManager;
import download.api.DownloadListener;
import download.impl.ConnectionManagerImpl;
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

//        String url = "http://upload-images.jianshu.io/upload_images/430632-49ce383d76352277.jpg";
//        String url = "http://img3.91.com/uploads/allimg/130428/32-13042Q63239.jpg";
//        String url = "http://images.weiphone.net/data/attachment/forum/201703/10/082621it8dfr8frmpbgrdo.png";
        String url = "http://img.zhxhlm.com/o_1b4sfgd8087os528sg85jjkf.mp4";
        FileDownloader downloader = new FileDownloader(url);
        ConnectionManager cm = new ConnectionManagerImpl();
        downloader.setConnectionManager(cm);
        downloader.setListener(new DownloadListener() {

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
