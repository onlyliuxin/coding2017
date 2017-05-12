package com.circle.download.api;

import java.io.IOException;

/**
 * Created by keweiyang on 2017/3/10.
 */
public interface Connection {

    /**
     * 给定开始和结束位置，读取数据，返回值是字节数组
     *
     * @param startPos 开始位置，从0开始
     * @param endPos   结束位置
     * @return
     * @throws IOException
     */
    public void read(int startPos, int endPos) throws IOException, ConnectionException;

    /**
     * 得到数据内容的长度
     * @return
     */
    public int getContentLength();

    /**
     * 关闭连接
     */
    public void close();

}
