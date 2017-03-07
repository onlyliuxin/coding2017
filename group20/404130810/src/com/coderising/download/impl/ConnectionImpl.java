package com.coderising.download.impl;

import java.io.IOException;

import com.coderising.download.api.Connection;

public class ConnectionImpl implements Connection{

	private byte[] fileContent;

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		return null;
	}

	@Override
	public int getContentLength() {
		return fileContent.length;
	}

	@Override
	public void close() {
		
	}
	
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

}