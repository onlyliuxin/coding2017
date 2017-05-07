package com.pxshuo.test;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;


/**
 * 测试实现下载功能
 * @author Pxshuo
 *
 */

public class ConnectTest {
	
	public static void main(String[] args) {
		ConnectTest test = new ConnectTest();
		String url = "https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/3c6d55fbb2fb4316352d920a22a4462309f7d394.jpg";
		try {
			//test.saveToFile("https://code.getmdl.io/1.3.0/mdl-template-dashboard.zip", "./down.zip");
			//test.saveToFile("https://gss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/3c6d55fbb2fb4316352d920a22a4462309f7d394.jpg", "./down.png");
//			ConnectionImpl ci = new ConnectionImpl(url);
//			FileOutputStream fos = new FileOutputStream("down.png");
//			int length = ci.getContentLength();
//			System.out.println(length);
//			fos.write(ci.read(0, length - 1),0,length);
//			fos.close();
			System.out.println("success");
			
			URL url2 = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
	        //connection.setRequestMethod("GET");
	        //connection.setConnectTimeout(10000);
			
	        FileOutputStream fos = null;
			BufferedInputStream bis = null;
			byte[] buf = new byte[connection.getContentLength()];
			bis = new BufferedInputStream(connection.getInputStream());
			//建立文件
			fos = new FileOutputStream("down2.png");
			int size = 0;//= bis.read(buf);
			int offset = 0;
			while((size = bis.read(buf, 0, buf.length)) != -1){
			}
			fos.write(buf,0,buf.length);
			//保存文件
//			while((size = bis.read(buf)) != -1){
//				fos.write(buf,0,size);
//			}
			fos.close();
			bis.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public final static boolean DEBUG = true;
	private static int BUFFER_SIZE = 8096;//缓存大小
	private Vector vDownload = new Vector<>();//Url列表
	private Vector vFileList = new Vector<>();//文件名
	
	public ConnectTest() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 清除下载列表
	 */
	public void resetList(){
		vDownload.clear();
		vFileList.clear();
	}
	
	/**
	 * 增加下载列表项
	 * @param url
	 * @param filename
	 */
	public void addItem(String url,String filename){
		vDownload.add(url);
		vFileList.add(filename);
	}
	
	/**
	 * 根据列表下载资源
	 */
	public void downLoadByList() {
		String url = null;
		String filename = null;
		
		for(int i = 0;i < vDownload.size();i++){
			url = (String)vDownload.get(i);
			filename = (String)vFileList.get(i);
			
			try {
				saveToFile(url, filename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				if (DEBUG) {
					System.out.println("资源[" + url + "]下载失败！！！");
				}
				e.printStackTrace();
			}
		}
		
		if (DEBUG) {
			System.out.println("下载完成");
		}
	}
	
	public void saveToFile(String destUrl,String filename) throws IOException {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		
		//建立连接
		url = new URL(destUrl);
		httpUrl = (HttpURLConnection)url.openConnection();
		//链接指定的资源
		//httpUrl.connect();
		//获取网络输入流
		bis = new BufferedInputStream(httpUrl.getInputStream());
		//建立文件
		fos = new FileOutputStream(filename);
		
		if (DEBUG) {
			System.out.println("正在获取链接[" + destUrl + "]的内容...\n将其保存为文件[" + filename + "]");
		}
		
		int byteLength = 0;
		
		//保存文件
		while((size = bis.read(buf)) != -1){
			fos.write(buf,0,size);
			byteLength += size;
		}
		System.out.println(httpUrl.getContentLength() + ":" + byteLength);
		fos.close();
		bis.close();
		httpUrl.disconnect();
	}
	
	public void saveToFile(String destUrl,String filename,int start,int end) throws IOException {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		
		//建立连接
		url = new URL(destUrl);
		httpUrl = (HttpURLConnection)url.openConnection();
		//链接指定的资源
		httpUrl.connect();
		httpUrl.setRequestProperty("Range", "bytes=" + start + "-" + end);
		//获取网络输入流
		bis = new BufferedInputStream(httpUrl.getInputStream());
		//建立文件
		fos = new FileOutputStream(filename);
		
		if (DEBUG) {
			System.out.println("正在获取链接[" + destUrl + "]的内容...\n将其保存为文件[" + filename + "]");
		}
		
		//保存文件
		while((size = bis.read(buf)) != -1){
			fos.write(buf,0,size);
		}
		
		fos.close();
		bis.close();
		httpUrl.disconnect();
	}
}
