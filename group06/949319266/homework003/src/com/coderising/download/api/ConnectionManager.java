package com.coderising.download.api;

public interface ConnectionManager {
    /**
     * 给定一个url , 打开一个连接

     */
    Connection open(String url, int startPos, int endPos) throws ConnectionException;
    /**
     * 获取文件长度
     */
    int getContentLength(String url) throws ConnectionException;

}
