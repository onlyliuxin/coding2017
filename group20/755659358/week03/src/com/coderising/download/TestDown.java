package com.coderising.download;

import com.coderising.download.api.ConnectionManager;
import com.coderising.download.impl.ConnectionManagerImpl;

public class TestDown {

	public static void main(String[] args) {
		String url = "http://a.zdmimg.com/201703/11/58c3ff6f422a38093.jpg_c350.jpg";

		FileDownloader downloader = new FileDownloader(url);

		ConnectionManager cm = new ConnectionManagerImpl();
		downloader.setConnectionManager(cm);

		downloader.execute();

	}

}
