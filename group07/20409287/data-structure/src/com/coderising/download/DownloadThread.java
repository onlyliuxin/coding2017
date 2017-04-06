package com.coderising.download;


import com.coderising.download.api.Connection;

import java.io.*;

public class DownloadThread extends Thread {

    private Connection conn;
    private int startPos;
    private int endPos;

    private RandomAccessFile downFile;

    public DownloadThread(Connection conn, int startPos, int endPos, RandomAccessFile downFile) throws FileNotFoundException {

        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        this.downFile = downFile;
    }

    public void run() {

        String threadName = Thread.currentThread().getName();
        System.out.println("线程" + threadName + "开始下载");
        try {
            byte[] data = conn.read(startPos, endPos);
            File file = new File(threadName + "_record.dat");
            if (file.exists() && file.length() > 0) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(file)));
                String saveStartPos = br.readLine();
                if (saveStartPos != null && saveStartPos.length() > 0) {
                    startPos = Integer.parseInt(saveStartPos);
                }
            }
            // 将数据写入文件并记录位置
            RandomAccessFile recordFile = new RandomAccessFile(file, "rwd");// 存储下载标记的文件
            downFile.seek(startPos);
            System.out.println("当前文件指针：" + startPos);
            System.out.println("要写入的内容:" + new String(data));
            downFile.write(data);
            // 将下载标记存入指定文档
            String savaPoint = String.valueOf(data.length + 1);
            recordFile.write(savaPoint.getBytes());
        } catch (IOException e) {
            System.out.println("读取失败: " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }

    }



}
