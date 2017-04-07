package com.hoo.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.hoo.util.LogUtils;

/**
 * <b>function:</b> 单线程下载文件
 * 
 * @author hoojo
 * @createDate 2011-9-22 下午02:55:10
 * @file DownloadFile.java
 * @package com.hoo.download
 * @project MultiThreadDownLoad
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class DownloadFile extends Thread {

	// 下载文件url
	private String url;
	// 下载文件起始位置
	private long startPos;
	// 下载文件结束位置
	private long endPos;
	// 线程id
	private int threadId;

	// 下载是否完成
	private boolean isDownloadOver = false;

	private SaveItemFile itemFile;

	private static final int BUFF_LENGTH = 1024 * 8;

	/**
	 * @param url
	 *            下载文件url
	 * @param name
	 *            文件名称
	 * @param startPos
	 *            下载文件起点
	 * @param endPos
	 *            下载文件结束点
	 * @param threadId
	 *            线程id
	 * @throws IOException
	 */
	public DownloadFile(String url, String name, long startPos, long endPos, int threadId) throws IOException {
		super();
		this.url = url;
		this.startPos = startPos;
		this.endPos = endPos;
		this.threadId = threadId;
		// 分块下载写入文件内容
		this.itemFile = new SaveItemFile(name, startPos);
	}

	@Override
	public void run() {
		while (endPos > startPos && !isDownloadOver) {
			try {
				URL url = new URL(this.url);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();

				// 设置连接超时时间为10000ms
				conn.setConnectTimeout(10000);
				// 设置读取数据超时时间为10000ms
				conn.setReadTimeout(10000);

				setHeader(conn);

				String property = "bytes=" + startPos + "-";
				conn.setRequestProperty("RANGE", property);

				// 输出log信息
				LogUtils.log("开始 " + threadId + "：" + property + endPos);
				// printHeader(conn);

				// 获取文件输入流，读取文件内容
				InputStream is = conn.getInputStream();

				byte[] buff = new byte[BUFF_LENGTH];
				int length = -1;
				LogUtils.log("#start#Thread: " + threadId + ", startPos: " + startPos + ", endPos: " + endPos);
				while ((length = is.read(buff)) > 0 && startPos < endPos && !isDownloadOver) {
					// 写入文件内容，返回最后写入的长度
					startPos += itemFile.write(buff, 0, length);
				}
				LogUtils.log("#over#Thread: " + threadId + ", startPos: " + startPos + ", endPos: " + endPos);
				LogUtils.log("Thread " + threadId + " is execute over!");
				this.isDownloadOver = true;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (itemFile != null) {
						itemFile.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (endPos < startPos && !isDownloadOver) {
			LogUtils.log("Thread " + threadId + " startPos > endPos, not need download file !");
			this.isDownloadOver = true;
		}
		if (endPos == startPos && !isDownloadOver) {
			LogUtils.log("Thread " + threadId + " startPos = endPos, not need download file !");
			this.isDownloadOver = true;
		}
	}

	/**
	 * <b>function:</b> 打印下载文件头部信息
	 * 
	 * @author hoojo
	 * @createDate 2011-9-22 下午05:44:35
	 * @param conn
	 *            HttpURLConnection
	 */
	public static void printHeader(URLConnection conn) {
		int i = 1;
		while (true) {
			String header = conn.getHeaderFieldKey(i);
			i++;
			if (header != null) {
				LogUtils.info(header + ":" + conn.getHeaderField(i));
			} else {
				break;
			}
		}
	}

	/**
	 * <b>function:</b> 设置URLConnection的头部信息，伪装请求信息
	 * 
	 * @author hoojo
	 * @createDate 2011-9-28 下午05:29:43
	 * @param con
	 */
	public static void setHeader(URLConnection conn) {
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");
		conn.setRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
		conn.setRequestProperty("Accept-Encoding", "utf-8");
		conn.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		conn.setRequestProperty("Keep-Alive", "300");
		conn.setRequestProperty("connnection", "keep-alive");
		conn.setRequestProperty("If-Modified-Since", "Fri, 02 Jan 2009 17:00:05 GMT");
		conn.setRequestProperty("If-None-Match", "\"1261d8-4290-df64d224\"");
		conn.setRequestProperty("Cache-conntrol", "max-age=0");
		conn.setRequestProperty("Referer", "https://www.github.com");
	}

	public boolean isDownloadOver() {
		return isDownloadOver;
	}

	public long getStartPos() {
		return startPos;
	}

	public long getEndPos() {
		return endPos;
	}
}