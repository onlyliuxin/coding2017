package com.coderising.multiThreadDownloader;


import org.junit.Test;

public class MultiThreadDownloaderTest {

	@Test
	public void testDownload() {
		MultiThreadDownloader downloader=new MultiThreadDownloader();
		downloader.download();
//		System.out.println(this.getClass().getResource("").getPath());
	}

}
