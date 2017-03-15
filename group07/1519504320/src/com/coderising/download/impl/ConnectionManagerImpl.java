package com.coderising.download.impl;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;

import java.io.IOException;

public class ConnectionManagerImpl implements ConnectionManager {


	@Override
	public Connection open(String url) throws ConnectionException {
		if(!url.equals("")&&url!=null){
			return new Connection() {
				@Override
				public byte[] read(int startPos, int endPos) throws IOException {
					return new byte[0];
				}

				@Override
				public int getContentLength() {
					return 0;
				}

				@Override
				public void close() {

				}
			};
		}
		throw new ConnectionException();
	}

}
