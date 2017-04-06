package cn.net.pikachu.download.impl;


import cn.net.pikachu.download.DownloadThread;
import cn.net.pikachu.download.api.Connection;
import cn.net.pikachu.download.api.ConnectionException;
import cn.net.pikachu.download.api.ConnectionManager;
import okhttp3.Request;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ConnectionManagerImpl implements ConnectionManager {

    @Override
	public Connection open(String url) throws ConnectionException {
		return new ConnectionImpl(url);
	}
}
