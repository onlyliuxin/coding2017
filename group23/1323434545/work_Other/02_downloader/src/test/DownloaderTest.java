package test;

import java.io.File;

import api.ConnectionException;
import api.DownloadListener;
import main.FileDownloader;

public class DownloaderTest {
	public static void main(String[] args) throws ConnectionException {
		FileDownloader fd = new FileDownloader("http://mirror.bit.edu.cn/apache/tomcat/tomcat-8/v8.5.13/bin/apache-tomcat-8.5.13.exe",new File("E:\\tomcat.exe"));
		fd.setListener(new DownloadListener() {
			@Override
			public void notifyFinished() {
				System.out.println("下载完了!");
			}
		});
		fd.execute();
	}

}
