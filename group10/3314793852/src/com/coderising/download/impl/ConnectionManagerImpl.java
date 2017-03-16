	/*
	 * 该类用于产生URL资源连接ConnectionImpl类的对象。
	 */
	package com.coderising.download.impl;
	
	import java.io.IOException;
	import java.net.MalformedURLException;
	
	import java.net.URL;				//URL网络资源连接类。
	import java.net.URLConnection;
	
	import com.coderising.download.api.Connection;
	import com.coderising.download.api.ConnectionException;
	import com.coderising.download.api.ConnectionManager;


	
	public class ConnectionManagerImpl implements ConnectionManager {
		
		URL aURL=null;
		URLConnection URLCn=null;
		Connection cn=null;
		
		@Override
		public Connection open(String url){
			//打开一个网络资源。
			try {
				aURL=new URL(url);
				URLCn= aURL.openConnection();
				cn=new ConnectionImpl(URLCn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cn;
		}
	
	}
