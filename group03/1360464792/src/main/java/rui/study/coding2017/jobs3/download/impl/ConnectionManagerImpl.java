package rui.study.coding2017.jobs3.download.impl;


import rui.study.coding2017.jobs3.download.api.Connection;
import rui.study.coding2017.jobs3.download.api.ConnectionException;
import rui.study.coding2017.jobs3.download.api.ConnectionManager;

import java.io.IOException;
import java.net.URL;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException, IOException {
		return new ConnectionImpl(new URL(url));
	}

}
