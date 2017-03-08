package com.coderising.download.impl;

import com.coderising.download.FileDownloader;
import com.coderising.download.api.DownloadListener;

public class DownloadThreadListener implements DownloadListener {

	
	public DownloadThreadListener() {
	}

	private FileDownloader fileDownloader;
	public DownloadThreadListener(FileDownloader fileDownloader) {
		this.fileDownloader = fileDownloader;
	}

	@Override
	public void notifyFinished() {
		System.out.println("noted");
		fileDownloader.setFinished();
	}

}
