package download.api;

public interface ConnectionManager {
	/**
	 * 打开一个连接
	 * @param url url
	 * @param startPos 开始位置
	 * @param endPos 结束位置
	 * @return 打开的连接
	 * @throws ConnectionException 参数错误
	 */
	Connection open(String url, int startPos, int endPos) throws ConnectionException;
}
