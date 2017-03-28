package com.coderising.download.api;

public interface DownloadListener {
	
	/**
	 * @param percent 下载百分比
	 * @param downloadSpeed 下载速度，单位M/s
	 */
	public void notifyFinished(int percent,String downloadSpeed);
}
