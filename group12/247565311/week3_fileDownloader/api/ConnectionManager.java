package week3_fileDownloader.api;

public interface ConnectionManager {
	/**
	 * 缁欏畾涓�釜url , 鎵撳紑涓�釜杩炴帴
	 * @param url
	 * @return
	 */
	public Connection open(String url) throws ConnectionException;	
}
