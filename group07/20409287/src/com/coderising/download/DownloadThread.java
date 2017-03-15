package com.coderising.download;


import xdx.homework.third.download.api.Connection;

import java.io.*;

public class DownloadThread extends Thread {

    Connection conn;
    int startPos;
    int endPos;

    private RandomAccessFile downFile;

    private RandomAccessFile recordFile;

    public DownloadThread(Connection conn, int startPos, int endPos, String fileName) throws FileNotFoundException {

        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
        downFile = new RandomAccessFile(fileName, "rwd");
    }

    public void run() {

        System.out.println("线程" + Thread.currentThread().getName() + "开始下载");
        try {
            byte[] data = conn.read(startPos, endPos);
            File file = new File("record.dat");
            if (file.exists() && file.length() > 0) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(new FileInputStream(file)));
                String saveStartPos = br.readLine();
                if (saveStartPos != null && saveStartPos.length() > 0) {
                    startPos = Integer.parseInt(saveStartPos);
                }
            }
            // 将数据写入文件并记录位置
            recordFile = new RandomAccessFile("record.dat", "rwd");// 存储下载标记的文件
            downFile.write(data, 0, data.length);
            // 将下载标记存入指定文档
            String savaPoint = String.valueOf(data.length + 1);
            recordFile.write(savaPoint.getBytes());
        } catch (IOException e) {
            System.out.println("读取失败: " + e.getMessage());
        } finally {
            try {
                recordFile.close();
                conn.close();
            } catch (IOException e) {
            }
        }

    }



}
