package com.basic.week3.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.URL;

public class Demo {

	public static void main(String[] args) throws Exception {
//		File f=new File("E://123.txt");
//		InputStream ii=new FileInputStream(f);
//		byte [] b=new byte [2];
//		int j;
//		while(true){
//			if(ii.read(b)==0){
//				System.out.println("1111");
//			}
//			if(ii.read(b)==-1){
//				System.out.println("3333");
//			}
//		}
		URL url=new URL("Http://localhsot:8080/123.txt");
		String [] s=url.toString().split("/");
		for(String str:s){
			
		}
	}

}
