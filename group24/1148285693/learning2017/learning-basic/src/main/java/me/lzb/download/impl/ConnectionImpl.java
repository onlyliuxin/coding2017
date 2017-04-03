package me.lzb.download.impl;

import me.lzb.download.api.Connection;
import me.lzb.download.api.ConnectionException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


class ConnectionImpl implements Connection {


    static final int BUFFER_SIZE = 1024;

    HttpGet httpget;

    CloseableHttpClient httpClient;



    public ConnectionImpl(String url) throws ConnectionException{
        httpget = new HttpGet(url);
        httpget.setHeader("Accept-Encoding", "identity");
        httpClient = HttpClients.createDefault();
    }


	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

        httpget.removeHeaders("Range");
        httpget.addHeader("Range", "bytes=" + startPos + "-" + endPos);

        CloseableHttpResponse response = httpClient.execute(httpget);
        InputStream inputStream  = response.getEntity().getContent();

        byte[] buff = new byte[BUFFER_SIZE];

        int lenth = endPos - startPos + 1;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        while(baos.size() < lenth){

            int len = inputStream.read(buff);
            if (len < 0) {
                break;
            }
            baos.write(buff,0, len);
        }

        if(baos.size() > lenth){
            byte[] data = baos.toByteArray();
            return Arrays.copyOf(data, lenth);
        }

        return baos.toByteArray();
	}

	@Override
	public int getContentLength() {

        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();

            return -1;
        }

        HttpEntity httpEntity = response.getEntity();
        return (int) httpEntity.getContentLength();
    }

	@Override
	public void close() {

    }





}
