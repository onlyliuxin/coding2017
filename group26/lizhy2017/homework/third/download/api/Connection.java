package third.download.api;

import java.io.IOException;

public interface Connection {
    /**
     * 给定开始和结束位置， 读取数据， 返回值是字节数组
     *
     * @param startPos 开始位置， 从0开始
     * @param endPos   结束位置
     * @return 返回的字节数组
     */
    public byte[] read(int startPos, int endPos) throws IOException, ConnectionException;

    /**
     * 得到数据内容的长度
     *
     * @return 资源的总长度
     */
    public int getContentLength();

    /**
     * 关闭连接
     */
    public void close();

    public String getFileName();
}
