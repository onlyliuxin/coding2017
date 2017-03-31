package third.download.api;

public interface ConnectionManager {
    /**
     * 给定一个url , 打开一个连接
     *
     * @param url url
     * @return 连接
     */
    public Connection open(String url) throws ConnectionException;

    /**
     * 获取长度
     */
    int getContentLength(String urlStr) throws ConnectionException;
}
