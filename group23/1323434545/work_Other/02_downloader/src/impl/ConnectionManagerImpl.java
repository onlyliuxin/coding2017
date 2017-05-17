package impl;



import api.Connection;
import api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) {
		return new ConnectionImpl(url);
	}

}