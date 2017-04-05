package week3.com.coding.download;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import week3.com.coding.download.api.Connection;

public class DownloadThread extends Thread
{
    
    Connection conn;
    
    int startPos;
    
    int endPos;
    
    public DownloadThread(Connection conn, int startPos, int endPos)
    {
        
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
    }
    
    /**
     * �߳�ִ�з����������̶߳�ȡһ�����ȵ��ֽڣ���д���ļ���
     */
    public void run()
    {
        File f = new File("d:\\test.txt");
        RandomAccessFile raf = null;
        try
        {
            raf = new RandomAccessFile(f, "rwd");
            raf.seek(startPos);// ��λ��ǰ��ָ��
            // raf.close();
            byte[] bs = conn.read(startPos, endPos);
            raf.write(bs);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * ��Դ�ͷ�
     * 
     * @param raf
     * @param conn
     */
    public void release(RandomAccessFile raf, Connection conn)
    {
        try
        {
            raf.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        conn.close();
    }
}
