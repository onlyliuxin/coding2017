
package com.hoo.entity;

/**
 * <b>function:</b> 下载文件信息类
 * 
 * @author hoojo
 * @createDate 2011-9-21 下午05:14:58
 * @file DownloadInfo.java
 * @package com.hoo.entity
 * @project MultiThreadDownLoad
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class DownloadInfo {
	// 下载文件url
	private String url;
	// 下载文件名称
	private String fileName;
	// 下载文件路径
	private String filePath;
	// 分成多少段下载， 每一段用一个线程完成下载
	private int splitter;

	// 下载文件默认保存路径
	private final static String FILE_PATH = "C:/temp";
	// 默认分块数、线程数
	private final static int SPLITTER_NUM = 5;

	public DownloadInfo() {
		super();
	}

	/**
	 * @param url
	 *            下载地址
	 */
	public DownloadInfo(String url) {
		this(url, null, null, SPLITTER_NUM);
	}

	/**
	 * @param url
	 *            下载地址url
	 * @param splitter
	 *            分成多少段或是多少个线程下载
	 */
	public DownloadInfo(String url, int splitter) {
		this(url, null, null, splitter);
	}

	/***
	 * @param url
	 *            下载地址
	 * @param fileName
	 *            文件名称
	 * @param filePath
	 *            文件保存路径
	 * @param splitter
	 *            分成多少段或是多少个线程下载
	 */
	public DownloadInfo(String url, String fileName, String filePath, int splitter) {
		super();
		if (url == null || "".equals(url)) {
			throw new RuntimeException("url is not null!");
		}
		this.url = url;
		this.fileName = (fileName == null || "".equals(fileName)) ? getFileName(url) : fileName;
		this.filePath = (filePath == null || "".equals(filePath)) ? FILE_PATH : filePath;
		this.splitter = (splitter < 1) ? SPLITTER_NUM : splitter;
	}

	/**
	 * <b>function:</b> 通过url获得文件名称
	 * 
	 * @author hoojo
	 * @createDate 2011-9-30 下午05:00:00
	 * @param url
	 * @return
	 */
	private String getFileName(String url) {
		return url.substring(url.lastIndexOf("/") + 1, url.length());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if (url == null || "".equals(url)) {
			throw new RuntimeException("url is not null!");
		}
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = (fileName == null || "".equals(fileName)) ? getFileName(url) : fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = (filePath == null || "".equals(filePath)) ? FILE_PATH : filePath;
	}

	public int getSplitter() {
		return splitter;
	}

	public void setSplitter(int splitter) {
		this.splitter = (splitter < 1) ? SPLITTER_NUM : splitter;
	}

	@Override
	public String toString() {
		return this.url + "#" + this.fileName + "#" + this.filePath + "#" + this.splitter;
	}
}
