package com.hoo.util;

import com.hoo.download.BatchDownloadFile;
import com.hoo.entity.DownloadInfo;

/**
 * <b>function:</b> 分块多线程下载工具类
 * 
 * @author hoojo
 * @createDate 2011-9-28 下午05:22:18
 * @file DownloadUtils.java
 * @package com.hoo.util
 * @project MultiThreadDownLoad
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public abstract class DownloadUtils {

	public static void download(String url) {
		DownloadInfo bean = new DownloadInfo(url);
		LogUtils.info(bean);
		BatchDownloadFile down = new BatchDownloadFile(bean);
		new Thread(down).start();
	}

	public static void download(String url, int threadNum) {
		DownloadInfo bean = new DownloadInfo(url, threadNum);
		LogUtils.info(bean);
		BatchDownloadFile down = new BatchDownloadFile(bean);
		new Thread(down).start();
	}

	public static void download(String url, String fileName, String filePath, int threadNum) {
		DownloadInfo bean = new DownloadInfo(url, fileName, filePath, threadNum);
		LogUtils.info(bean);
		BatchDownloadFile down = new BatchDownloadFile(bean);
		new Thread(down).start();
	}
}