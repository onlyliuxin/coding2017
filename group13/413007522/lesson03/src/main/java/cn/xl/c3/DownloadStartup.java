package cn.xl.c3;

public class DownloadStartup { 
	private static final String encoding = "utf-8"; 
	
	public static void main(String[] args) { 
		DownloadTask downloadManager = new DownloadTask(5); 
		String urlStr = "http://imgadmin.voole.com/img/pic/2017/03/21/1000/2017032117552710008ww5f.jpg"; 
		//downloadManager.setThreadNum(1);
		downloadManager.setSleepSeconds(5); 
		downloadManager.setFileDir("E://");
		downloadManager.download(urlStr, encoding); 
	} 
}
