	
	package com.coderising.download.impl;
	
	import java.io.IOException;
	import java.io.InputStream;
	import java.net.URLConnection;

	import com.coderising.download.api.Connection;
	
	
	public class ConnectionImpl implements Connection{
		
		URLConnection urlCon=null;	//URLConnection对象用于连接网络资源。
		
		//构造方法，参数为URLConnection的对象。
		public ConnectionImpl(URLConnection urlCon){
			this.urlCon=urlCon;
		}
		
		//该方法读取网络资源的数据，并将数据已byte[]数组的形式缓存，并返回。
		@Override
		public byte[] read(int startPos, int endPos) throws IOException {
			
			int currentLength=endPos-startPos+1;		//当前线程的要下载的数据的长度。
			byte[] buf=new byte[currentLength];	//输入流缓存数组buff.
			InputStream iS=urlCon.getInputStream();	//将网络资源已输入流形式开始读入。
			
			iS.skip(startPos);						//定位到当前线程的下载开始位置。
			
			for(int i=0;i<currentLength;i++){		//开始将数据读从当前位置开始入byte[] buff数组
				buf[i]=(byte) iS.read();
			}
			
//			if(iS!=null){							//读入操作结束，关闭输入流。
//				iS.close();
//			}
			
			
			return buf;							//返回读取的数组。
		}
		
		//获得当前网络资源的数据长度。
		@Override
		public int getContentLength() {
			return urlCon.getContentLength();
		}
		
		//当前资源接口关闭。
		@Override
		public void close() {
			urlCon=null;
		}
	
	}
