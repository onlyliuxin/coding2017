/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.System.in;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javaclass.download.api.ConnectionManager;
import javaclass.download.api.DownloadListener;
import javaclass.download.impl.ConnectionManagerImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author CJ
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
    public void testDownload() throws IOException, NoSuchAlgorithmException {

        String url = "http://pineappledb.xyz/test.txt";

        FileDownloader downloader = new FileDownloader(url);

        ConnectionManager cm = new ConnectionManagerImpl();
        downloader.setConnectionManager(cm);

        downloader.setListener(new DownloadListener() {
            @Override
            public void notifyFinished() {
                downloadFinished = true;
            }

        });

        downloader.setOutputFile(new File("C:\\Users\\CJ\\Desktop\\test000.txt"));
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

        File downloadFile = downloader.getOutputFile();
        File expectedFile = new File("C:\\Users\\CJ\\Desktop\\test.txt");

        long downloadSize = downloadFile.length();
        long expectedSize = expectedFile.length();

        Assert.assertEquals(expectedSize, downloadSize);
        FileInputStream downloadin = new FileInputStream(downloadFile);
        FileInputStream expectedin = new FileInputStream(expectedFile);


        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(downloadin.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, downloadFile.length()));
        BigInteger downloadbi = new BigInteger(1, md5.digest());
        md5.update(expectedin.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, expectedFile.length()));
        BigInteger expectedbi = new BigInteger(1,md5.digest());
        Assert.assertEquals(downloadbi, expectedbi);

    }

}
