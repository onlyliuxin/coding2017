package xyy.download;

import xyy.download.api.Connection;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by 14258 on 2017/3/14.
 */
public class DownloadThread extends Thread {

    private int startPos;
    private int endPos;
    private boolean isDownloadEnd;
    private String threadName;
    private Connection connection;
    private  FileDownloader fileDownLoader;

    public DownloadThread(Connection connection, int startPos, int endPos, String threadName, FileDownloader fileDownloader) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.threadName = threadName;
        this.connection = connection;
        this.fileDownLoader = fileDownloader;
        this.setName(threadName);
    }


    @Override
    public void run(){
        try {
            byte [] data = connection.read(startPos,endPos);
            connection.close();
            System.out.println("下载线程名字"+threadName+"正在读取开始位置"+startPos+"结束位置"+endPos);

            int writelen=-1;
            RandomAccessFile randomAccessFile = null;
            randomAccessFile = new RandomAccessFile(fileDownLoader.fileName,"rw" );
            randomAccessFile.seek(startPos);
            randomAccessFile.write(data,0,data.length);
            writelen = data.length;

            isDownloadEnd = true;
            fileDownLoader.addDownNumber();











        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
