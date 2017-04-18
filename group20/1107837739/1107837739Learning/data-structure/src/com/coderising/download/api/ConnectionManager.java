package com.coderising.download.api;

public interface ConnectionManager {
    /**
     * 给定一个url , 打开一个连接
     *
     * @param url 连接地址
     * @param startPos 读取文件的起始位置
     * @param endPos 读取文件的结束位置
     * @return 连接
     */
    Connection open(String url, int startPos, int endPos) throws ConnectionException;

    /**
     * 获取文件长度
     *
     * @param url 连接地址
     * @return 文件长度
     */
    int getContentLength(String url) throws ConnectionException;
}