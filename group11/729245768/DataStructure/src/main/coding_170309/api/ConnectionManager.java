package main.coding_170309.api;

/**
 * Created by peter on 2017/3/9.
 */
public interface ConnectionManager {
    /**
     * 给定一个url , 打开一个连接
     * @param url
     * @return
     */
    public Connection open(String url) throws ConnectionException;
}
