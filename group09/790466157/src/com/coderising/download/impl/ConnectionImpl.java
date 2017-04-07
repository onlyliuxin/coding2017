package com.coderising.download.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import com.basic.ArrayList;
import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{
	private HttpURLConnection downLoadConn;
	private HttpURLConnection getLengthConn;

	public ConnectionImpl(URL urlObject) {
        HttpURLConnection conn = null;
        try {
            downLoadConn = (HttpURLConnection) urlObject.openConnection();
            downLoadConn.setRequestMethod("GET");

            getLengthConn = (HttpURLConnection) urlObject.openConnection();
            getLengthConn.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
        downLoadConn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

        InputStream in = downLoadConn.getInputStream();
        byte[] buf = new byte[endPos-startPos+1];
        byte[] tempBuf = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(in);
        int len = 0;
        int totalLen = 0;
        while((len=bis.read(tempBuf,0,tempBuf.length))!=-1){
            System.arraycopy(tempBuf, 0, buf, totalLen, len);
            totalLen += len;
        }
        String desc = " bytes=" + startPos + "-" + endPos + " ";
        System.out.println(Thread.currentThread().getName()+desc+totalLen);
        in.close();
        bis.close();
        return Arrays.copyOf(buf, totalLen);
    }

	@Override
	public int getContentLength() {
		int len = getLengthConn.getContentLength();
		return len;
	}

	@Override
	public void close() {
		downLoadConn.disconnect();
		getLengthConn.disconnect();
	}

}
