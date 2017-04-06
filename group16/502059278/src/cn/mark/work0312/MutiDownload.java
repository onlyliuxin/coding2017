package cn.mark.work0312;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 多线程下载
 */
public class MutiDownload {
	/*线程数*/
	private static final int THREAD_COUNT = 5;
	/*下载资源*/
	private static final String DOWNLOAD_URL    = "http://cn.bing.com/az/hprichbg/rb/PlungeDiving_ZH-CN11143756334_1920x1080.jpg";
	/*下载位置*/
	private static final String FILE_NAME = "D:/down.jpg";

	public static void main(String[] args) {
		//文件大小
		long fileSize;
		HttpURLConnection connection = null;
		try{
			//打开一个链接
			connection = (HttpURLConnection) new URL(DOWNLOAD_URL).openConnection();
			//设置请求方式
			connection.setRequestMethod("GET");
			//连接超时
			connection.setConnectTimeout(8000);
			//读取超时
			connection.setReadTimeout(8000);
			
			if ( connection.getResponseCode() == 200 ){//请求成功返回200
				//文件大小
				fileSize = connection.getContentLength();
				//每个线程要读取的块
				long eachSize = fileSize / THREAD_COUNT;
				
				//打开一个RandomAccessFile文件,打开方式为读写(rw)
				RandomAccessFile raf = new RandomAccessFile(FILE_NAME,"rw");
				//setLength是先在存储设备占用一块空间,防止下载到一半空间不足
				raf.setLength(fileSize);
				raf.close();
				
				/*创建线程开始下载*/
				for ( int i =0; i <THREAD_COUNT; i++ ){
					long startIndex = i * eachSize;
					long endIndex = (i+1) * eachSize -1;
					if ( i == THREAD_COUNT -1 ){ //最后一个线程直接下到文件末尾
						endIndex = fileSize;
					}
					new DownloadThread(DOWNLOAD_URL, FILE_NAME, i, startIndex, endIndex).start();
					
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if ( connection != null ){
				connection.disconnect();
				connection = null;
			}
		}

	}

}


class DownloadThread extends Thread{

	//下载地址
	private String url;
	//文件名
	private String fileName;
	//线程名
	private int threadID;
	//开始块
	private long startIndex;
	//结束块
	private long endIndex;
	//连接
	private HttpURLConnection connection;
	private RandomAccessFile raf;
	private InputStream inputStream;
	
	/**
	 * 构造方法
	 * @param url 下载路径
	 * @param fileName 下载文件名
	 * @param threadID 线程ID
	 * @param startIndex 开始块
	 * @param endIndex 结束块
	 */
	public DownloadThread(String url, String fileName, int threadID, long startIndex, long endIndex) {
		this.url = url;
		this.fileName = fileName;
		this.threadID = threadID;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	@Override
	public void run() {
		try {
			connection = (HttpURLConnection)new URL(url+"?ts="+System.currentTimeMillis()).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            //设置请求范围
            connection.setRequestProperty("RANGE", "bytes="+startIndex+"-"+endIndex);
            //请求部分数据成功，返回200
            int code = connection.getResponseCode();
            if (code == 200 || code == 206){
            	inputStream = connection.getInputStream();
            	byte[] bs = new byte[1024];
            	int len;
            	raf = new RandomAccessFile(fileName, "rwd");
            	//把开始位置设置为startIndex，与请求数据一致
            	raf.seek(startIndex);
            	long total = 0;
            	while ( (len = inputStream.read(bs)) != -1 ){
            		total += len;
            		System.out.println("线程"+threadID+":"+total);
            		raf.write(bs,0,len);
            	}
            }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if ( connection != null ){
				connection.disconnect();
				connection = null;
			}
			
			if ( raf != null ){
				try {
					raf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if ( inputStream != null ){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
	}
	
}
