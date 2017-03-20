package com.easy.util.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;

public class TestApp2 {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://om9xacvdp.bkt.clouddn.com/blog-self-logo.jpg");
		InputStream is=url.openStream();
		RandomAccessFile raf=new RandomAccessFile("d://11.jpg", "rwd");
		
	}
}
