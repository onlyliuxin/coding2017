package code03.v1.impl;

import code03.v1.api.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;


public class ConnectionImpl implements Connection{

	private static final Logger logger = LoggerFactory.getLogger(ConnectionImpl.class);


	private URLConnection urlConnection;
	private int length = -1;


    public ConnectionImpl(URLConnection urlConnection){
		this.urlConnection  = urlConnection;
	}

	/**
	 * 读取urlConnection.getInputStream()中的数据，返回byte[]
	 */
	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		int contentLength = getContentLength();
		if(startPos < 0 || endPos > contentLength || contentLength <= 0){
			logger.info("index out of range !");
			return null;
		}

		InputStream raw = null;
		BufferedInputStream in = null;
		int size = endPos - startPos + 1;
		byte[] data = new byte[size];
		try{
			raw = urlConnection.getInputStream();
			in = new BufferedInputStream(raw);
			in.skip(startPos);

			int offset = 0;
			while(offset < size){
				int bytesRead = in.read(data, offset, size - offset);
				while (bytesRead  == -1){break;}
				offset += bytesRead;
			}
            //用这种方式比较好
            /*
            int BUFFER_SIZE = 1024;
            byte[] buff = new byte[BUFFER_SIZE];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while(baos.size() < size){
                int bytesRead = in.read(buff); //缓存读取的数据，其SIZE大小不一定要等于总的SIZE
                if(bytesRead<0) break;
                baos.write(buff,0,bytesRead);
            }
            byte[] data = baos.toByteArray();
            Arrays.copyOf(data,size);
            */

        } catch (IOException e) {
			e.printStackTrace();
		}finally {
			raw.close();
			in.close();
		}
		return data;
	}

	@Override
	public int getContentLength() {
		if(length != -1){
			return length;
		}
		length = urlConnection.getContentLength();
		//if without content-length header
		if(length == -1) {
            throw new RuntimeException("content-length error");
        }
		return length;
	}

	@Override
	public void close() {
		if(urlConnection != null){
			urlConnection = null;
		}
	}

}