package org.wsc.coderising.download.impl;

import java.io.IOException;

import org.wsc.coderising.download.api.Connection;

/**
 *
 * 连接类
 * 
 * @author Administrator
 * @date 2017年3月6日下午7:10:13
 * @version v1.0
 *
 */
public class ConnectionImpl implements Connection {

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {

		return null;
	}

	@Override
	public int getContentLength() {

		return 0;
	}

	@Override
	public void close() {

	}

}
