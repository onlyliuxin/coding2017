package org.wsc.coderising.download.api;

/**
 *
 * 下载监听接口
 * 
 * @author Administrator
 * @date 2017年3月6日下午7:02:58
 * @version v1.0
 *
 */
public interface DownloadListener {
	/**
	 * 通知下载完成回调函数
	 */
	public void notifyFinished();
}
