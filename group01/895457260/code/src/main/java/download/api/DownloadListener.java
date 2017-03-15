package download.api;

public interface DownloadListener {
	/**
	 * 下载成功后自动调用此方法
	 */
	void notifyFinished();
}
