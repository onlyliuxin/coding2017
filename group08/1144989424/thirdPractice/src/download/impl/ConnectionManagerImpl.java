package download.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import download.api.Connection;
import download.api.ConnectionException;
import download.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
	    URL uu = null;
	    Connection conn = null;
	    try {
            uu = new URL(url);
            uu.getFile();
            URLConnection urlConn = uu.openConnection();
            int length = urlConn.getContentLength();
            System.out.println(length);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	    
		return conn;
	}
	
	public static void main(String [] args){
	    ConnectionManagerImpl cmi = new ConnectionManagerImpl();
	    try {
            cmi.open("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        } catch (ConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
