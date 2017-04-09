/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.download.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URLConnection;
import javaclass.download.api.Connection;

public class ConnectionImpl implements Connection {
    
    private URLConnection uc;
    private int curPos;
//    private ;
    BufferedInputStream bis;
    public ConnectionImpl(URLConnection uc) throws IOException{
        this.uc = uc;
        this.curPos = 0;
        bis = new BufferedInputStream(uc.getInputStream());
    }
    
    @Override
    public byte[] read(int startPos, int endPos) throws IOException {
        // 调整位置
//        bis.skip(startPos-this.curPos+1);
//        bis.skip(startPos-this.curPos);
//        this.curPos = endPos;
        
        // 因为只会read一次
         bis.skip(startPos);

        int readSize = endPos - startPos+1;
        byte[] bufferedByte = new byte[readSize];

        
        int getSize = bis.read(bufferedByte);
        System.err.println("Start Pos And End Pos:"+startPos+"\t"+endPos);
        System.err.println("expected Size:"+readSize);
        System.err.println("Get Size:"+getSize);
        
        return bufferedByte;
//         = ;
//        return Arrays.copyOfRange(bufferedByte, 0, getSize);
    }

    @Override
    public int getContentLength() {

        return uc.getContentLength();
    }

    @Override
    public void close() {
        
    }

}
