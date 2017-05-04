package com.coderising.jvm.loader;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.annotation.Resources;





public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className)  {
		InputStream ips = null;
		ByteArrayOutputStream bao = null;
		try {
			String name=className.replace(".", "\\")+".class";
			for(int i=0;i<clzPaths.size();i++){
				String path=clzPaths.get(i)+"\\"+name;
				File file =new File(path);
				if(file.exists()){
					ips=new FileInputStream(file);
					byte[] b=new byte[1024];
					bao=new ByteArrayOutputStream();
					while(ips.read(b, 0, b.length)!=-1){
						bao.write(b);
					}
					return bao.toByteArray();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ips!=null){
				try {
					ips.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bao!=null){
				try {
					bao.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;	
		
		
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<clzPaths.size();i++){
			if(i==clzPaths.size()-1){
				sb.append(clzPaths.get(i));
			}
			else
			  sb.append(clzPaths.get(i)+";");
		}
		return sb.toString();
	}

	

	

}
