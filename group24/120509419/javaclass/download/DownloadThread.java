/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.download;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaclass.download.api.Connection;

public class DownloadThread extends Thread {

    Connection conn;
    int startPos;
    int endPos;

    int curStepByteSize = 1024*8;
//    byte[] stepByteArr;

    public File getCurTmpFile() {
        return curTmpFile;
    }
    File curTmpFile;

    public DownloadThread(Connection conn, int startPos, int endPos) {
        
//        stepByteArr = new byte[curStepByteSize];
        
        this.conn = conn;
        this.startPos = startPos;
        this.endPos = endPos;
//        curTmpFile =
    }

    @Override
    public void run() {
        
        try {
            curTmpFile = File.createTempFile("TBtools", "downloadTmp");
//            BufferedWriter bw = new BufferedWriter(new FileWriter(curTmpFile));
            FileOutputStream fos = new FileOutputStream(curTmpFile);
            
            
            // 一个线程 内 分批次下载，这样才不会挤爆内存
//            int size = endPos - startPos+1;
//            int stepCount = size / curStepByteSize;
////            ssint remainSize = size % curStepByteSize;
//            for (int i = 0; i < stepCount-1; i++) {
//                fos.write(conn.read(startPos+curStepByteSize*i, startPos+curStepByteSize*(i+1)-1));
//                System.err.printf("Start Pos: %d\tEnd Pos %d\n",startPos+curStepByteSize*i, startPos+curStepByteSize*(i+1)-1);
//            }
            
//            fos.write(conn.read(startPos+curStepByteSize*(stepCount-1),endPos));
//            System.err.printf("Start Pos: %d\tEnd Pos %d\n",startPos+curStepByteSize*(stepCount-1), endPos);

            fos.write(conn.read(startPos, endPos));


            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(DownloadThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
