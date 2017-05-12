package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
//		HttpGet request = new HttpGet(url);
//        String result = "";
//		try {
//			HttpResponse response = HttpClients.createDefault().execute(request);
//			if(response.getStatusLine().getStatusCode()==200){
//				result = EntityUtils.toString(response.getEntity());
//			}
//            System.out.println("result length is " + result.length());
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
        ConnectionImpl conn_impl = null;

        try {
            URL url_path = new URL(url);
            HttpURLConnection urlconnection = (HttpURLConnection) url_path.openConnection();
            conn_impl = new ConnectionImpl(urlconnection);
        } catch (IOException e) {

        }
		return conn_impl;
	}

}
