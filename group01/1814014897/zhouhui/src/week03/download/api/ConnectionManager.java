package week03.download.api;

public interface ConnectionManager {
	/**
	 * 给定一个url , 打开一个连接
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public Connection open(String url) throws ConnectionException;	
}
