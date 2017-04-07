package third.download.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.UnknownServiceException;

import third.download.api.Connection;
import third.download.api.ConnectionException;
import third.download.utils.HttpUtil;

public class ConnectionImpl implements Connection {
    private HttpURLConnection mConnection;
    private ByteArrayOutputStream bos;
    private InputStream is;

    public ConnectionImpl(HttpURLConnection connection) {
        super();
        this.mConnection = connection;
    }

    /**
     * 给定开始和结束位置， 读取数据， 返回值是字节数组
     *
     * @param startPos 开始位置， 从0开始
     * @param endPos   结束位置
     * @return 返回的字节数组
     */
    @Override
    public byte[] read(int startPos, int endPos) throws IOException, ConnectionException {
        mConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
        mConnection.setInstanceFollowRedirects(true);
        mConnection.setConnectTimeout(20000);
        HttpUtil.checkStatus(mConnection);
        byte[] bytes = new byte[Math.min(1024, endPos - startPos + 1)];
        is = this.getInputStream();
//        InputStream is = new BufferedInputStream(mConnection.getInputStream());
        bos = new ByteArrayOutputStream();

        int lenth = is.read(bytes);
        while (lenth != -1)
            bos.write(bytes, 0, lenth);

        return bos.toByteArray();
    }

    /**
     * 得到数据内容的长度
     *
     * @return 资源的总长度
     */
    @Override
    public int getContentLength() {
        return mConnection.getContentLength();
    }

    @Override
    public void close() {
        if (null != bos)
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        if (null != is)
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        if (null != mConnection)
            mConnection.disconnect();
    }


    @Override
    public String getFileName() {
        String fileName = null;
        String field = mConnection.getHeaderField("Content-Disposition");
        if(field == null ){
            String urlStr = mConnection.getURL().toString();
            fileName = urlStr.substring(urlStr.lastIndexOf("/")+1);
        }else{
            fileName=field.substring(field.indexOf("filename")+10, field.length()-1);
        }
        System.out.println(fileName);
        return fileName;
    }

    public InputStream getInputStream() throws IOException {
        throw new UnknownServiceException("protocol doesn\'t support input");
    }

}
