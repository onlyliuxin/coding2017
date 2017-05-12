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
	    ConnectionImpl conn = null;
	    try {
            uu = new URL(url);
            URLConnection urlConn = uu.openConnection();
            conn.setUrlConn(urlConn);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
