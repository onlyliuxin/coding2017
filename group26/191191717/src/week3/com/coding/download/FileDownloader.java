package week3.com.coding.download;

import java.io.IOException;

import week3.com.coding.download.api.Connection;
import week3.com.coding.download.api.ConnectionException;
import week3.com.coding.download.api.ConnectionManager;
import week3.com.coding.download.api.DownloadListener;
import week3.com.coding.download.impl.ConnectionImpl;
import week3.com.coding.download.impl.ConnectionManagerImpl;

public class FileDownloader
{
    
    String url;
    
    DownloadListener listener;
    
    ConnectionManager cm;
    
    int ThreadNum;
    
    public FileDownloader(String url, int threadNum)
    {
        super();
        this.url = url;
        ThreadNum = threadNum;
    }
    
    public void execute()
    {
        // 在这里实现你的代码， 注意： 需要用多线程实现下载
        // 这个类依赖于其他几个接口, 你需要写这几个接口的实现代码
        // (1) ConnectionManager , 可以打开一个连接，通过Connection可以读取其中的一段（用startPos, endPos来指定）
        // (2) DownloadListener, 由于是多线程下载， 调用这个类的客户端不知道什么时候结束，所以你需要实现当所有
        // 线程都执行完以后， 调用listener的notifiedFinished方法， 这样客户端就能收到通知。
        // 具体的实现思路：
        // 1. 需要调用ConnectionManager的open方法打开连接， 然后通过Connection.getContentLength方法获得文件的长度
        // 2. 至少启动3个线程下载， 注意每个线程需要先调用ConnectionManager的open方法
        // 然后调用read方法， read方法中有读取文件的开始位置和结束位置的参数， 返回值是byte[]数组
        // 3. 把byte数组写入到文件中
        // 4. 所有的线程都下载完成以后， 需要调用listener的notifiedFinished方法
        
        // 下面的代码是示例代码， 也就是说只有一个线程， 你需要改造成多线程的。
        Connection conn = null;
        try
        {
            conn = (ConnectionImpl)cm.open(this.url);
            int length = conn.getContentLength();// 获取文件的长度
            // 三个线程，每个线程下载长度要平均
            int blockSize = length / this.ThreadNum;
            for (int i = 1; i <= this.ThreadNum; i++)
            {
                int sPos = (i - 1) * blockSize;
                int ePos = i * blockSize - 1;
                // 如果是最后一个，则结束位置等于最后的地方
                if (i == this.ThreadNum)
                {
                    ePos = length;
                }
                new DownloadThread(conn, sPos, ePos).start();
            }
        }
        catch (ConnectionException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                conn.close();
            }
        }
        
    }
    
    public void setListener(DownloadListener listener)
    {
        this.listener = listener;
    }
    
    public void setConnectionManager(ConnectionManager ucm)
    {
        this.cm = ucm;
    }
    
    public DownloadListener getListener()
    {
        return this.listener;
    }
    
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public ConnectionManager getCm()
    {
        return cm;
    }

    public void setCm(ConnectionManager cm)
    {
        this.cm = cm;
    }

    public int getThreadNum()
    {
        return ThreadNum;
    }

    public void setThreadNum(int threadNum)
    {
        ThreadNum = threadNum;
    }

    public static void main(String[] args)
        throws ConnectionException, IOException
    {
        
        String url = "http://localhost:8088/JSPDemo/test.txt";
        // ConnectionImpl ci=(ConnectionImpl)cm.open(url);
        // System.out.println(new String(ci.read(2, 31)));
        // File f = new File("d:\\test.txt");
        // RandomAccessFile raf = new RandomAccessFile(f, "rwd");
        // raf.seek(raf.length());// 定位当前的指针
        
        FileDownloader downloader = new FileDownloader(url,3);
        downloader.setConnectionManager(new ConnectionManagerImpl());
        downloader.execute();
        // int length = conn.getContentLength();// 获取文件的长度
        // System.out.println("urlConn: " + length);
        // int blockSize = length / 3;
        
        // new DownloadThread(conn, 0, blockSize - 1).start();// 第一个线程
        // new DownloadThread(conn, blockSize, blockSize * 2 - 1).start();// 第二个线程
        // new DownloadThread(conn, blockSize * 2 , length - 1).start();// 第三个线程
    }
}
