package dataStruct.com.coderising.download;

import dataStruct.com.coderising.download.DownloadThread;
import dataStruct.com.coderising.download.api.Connection;
import dataStruct.com.coderising.download.api.DownloadListener;
import dataStruct.com.coderising.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by songbao.yang on 2017/3/11.
 */
public class DownloadThreadTest {

    private DownloadThread downloadThread;
    private final String url = "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D360/sign=9bb90992550fd9f9bf17536f152cd42b/9a504fc2d5628535959cf4cf94ef76c6a6ef63db.jpg";
    private final String path = "D:";
    DownloadListener listener;

    @Before
    public void setUp() throws Exception {
        String threadName = "DownloadThreadTest";
        ConnectionManagerImpl connectionManager = new ConnectionManagerImpl();
        Connection connection = connectionManager.open(url);
        File file = new File(path + File.separator + "meinv.jpg");
        int contentLength = connection.getContentLength();
        System.out.println(contentLength);

        Connection conn = connectionManager.open(url);
        downloadThread = new DownloadThread(conn, 0, contentLength, file, listener);
        downloadThread.setName(threadName);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void run() throws Exception {
        downloadThread.run();
    }

}