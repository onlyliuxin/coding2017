package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.test.ClassFileParser;



public class ClassFileLoader {
/*
	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		className = className.replace('.', File.separatorChar); 
		
		for(String path:this.clzPaths)
		{
			String clzFileName = path + File.separatorChar+className;
			byte[] codes = loadClassFile_V2(clzFileName);
			if(codes != null){
				return codes;
			}
			
		}
		
		return null;	
		
		
	}
	
	private byte[] loadClassFile_V2(String clzFileName) {
		File f= new File(clzFileName);
		try {
			return IOUtils.toByteArray(new FileInputStream(f));
			
		} catch (Exception e) {
			
			return null;
		}
		
	}

	//第一种加载类的方法
	private byte[] loadClassFile_V1(String clzFileName) {
		BufferedInputStream bis = null;
		try{
			File f = new File(clzFileName);
		bis = new BufferedInputStream(new FileInputStream(f));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer  = new byte[1024];
		int length = -1;
		while((length = bis.read(buffer))!=-1)
		{
			bos.write(buffer,0,length);
		
		}
		byte[] codes = bos.toByteArray();
		return codes;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
/*
	public void addClassPath(String path) {
		if(this.clzPaths.contains(path))
		{
			return;
		}
		this.clzPaths.add(path);
		
	}

	public String getClassPath() {
		int count = 0;
		String clzP = null;
		for(String clzPathName:clzPaths){
			if(count<clzPaths.size())
			{
				clzP = clzPathName+";";
			}
			clzP = clzPathName;
		}
		return clzP;
	}

	public byte[] readBinaryCode(String className) throws IOException {
		className = className.replace('.', File.separatorChar);
		for(String path:this.clzPaths){
			String clz = path+File.separatorChar+className;
			byte[] codes = loadClassFile_V2(clz);
			if(codes!=null){
				return codes;
			}
			
		}
		
		
		
		
		return null;
	}
	
	

	private byte[] loadClassFile_V2(String clz) throws IOException {
		BufferedInputStream in = null;
		
		try {
			File f = new File(clz);
			
			in = new BufferedInputStream(new FileInputStream(f));
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024] ;
			int length = -1;
			while((length = in.read(buffer))!=-1)
			{
				bos.write(buffer,0,length);
			
			}
			byte[] codes = bos.toByteArray();
			return codes;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	
	/*	
		
	}
	


	public void addClassPath(String path) {
		if (this.clzPaths.contains(path)) {
			return;
		}
		this.clzPaths.add(path);
		
	}
	
	
	
	public String getClassPath(){
		StringBuffer buffer = new StringBuffer();
		for(int i = 0;i<this.clzPaths.size();i++)
		{
			buffer.append(this.clzPaths.get(i));
			if(i<this.clzPaths.size()-1){
				buffer.append(";");
			}
			
			
		}
		return buffer.toString();
	}
	public String getClassPathV1(){
		return StringUtils.join(this.clzPaths,";");
	}
*//*
	public ClassFile loadClass(String className) throws IOException {
		//变成字节数组
		byte[] codes = this.readBinaryCode(className);
		
		ClassFileParser parser = new ClassFileParser();
		//把字节数组传给解析类
		return parser.parse(codes);
		
	}*/

	private List<String> clzPaths = new ArrayList<String>();
	/*
	public byte[] readBinaryCode(String className) {
		className = className.replace('.', File.separatorChar); 
		
		for(String path:this.clzPaths)
		{
			String clzFileName = path + File.separatorChar+className;
			byte[] codes = loadClassFile_V2(clzFileName);
			if(codes != null){
				return codes;
			}
			
		}
		
		return null;	
		
		
	}
	
	private byte[] loadClassFile_V2(String clzFileName) {
		File f= new File(clzFileName);
		try {
			return IOUtils.toByteArray(new FileInputStream(f));
			
		} catch (Exception e) {
			
			return null;
		}
		
	}

	//第一种加载类的方法
	private byte[] loadClassFile_V1(String clzFileName) {
		/*BufferedInputStream bis = null;
		try{
			File f = new File(clzFileName);
		bis = new BufferedInputStream(new FileInputStream(f));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer  = new byte[1024];
		int length = -1;
		while((length = bis.read(buffer))!=-1)
		{
			bos.write(buffer,0,length);
		
		}
		byte[] codes = bos.toByteArray();
		return codes;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}*/

	public void addClassPath(String path) {
		if(this.clzPaths.contains(path))
		{
			return;
		}
		this.clzPaths.add(path);
		
	}

	public String getClassPath() {
		int count = 0;
		String clzP = null;
		for(String clzPathName:clzPaths){
			if(count<clzPaths.size())
			{
				clzP = clzPathName+";";
			}
			clzP = clzPathName;
		}
		return clzP;
	}

	public byte[] readBinaryCode(String className) throws IOException {
		className = className.replace('.', File.separatorChar);
		for(String path:this.clzPaths){
			String clz = path+File.separatorChar+className+".class";
			byte[] codes = loadClassFile_V2(clz);
			if(codes!=null){
				return codes;
			}
			
		}
		
		
		
		
		return null;
	}
	
	

	private byte[] loadClassFile_V2(String clz) throws IOException {
		BufferedInputStream in = null;
		
		try {
			File f = new File(clz);
			
			in = new BufferedInputStream(new FileInputStream(f));
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024] ;
			int length = -1;
			while((length = in.read(buffer))!=-1)
			{
				bos.write(buffer,0,length);
			
			}
			byte[] codes = bos.toByteArray();
			return codes;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	
	/*	
		
	}
	


	public void addClassPath(String path) {
		if (this.clzPaths.contains(path)) {
			return;
		}
		this.clzPaths.add(path);
		
	}
	
	
	
	public String getClassPath(){
		StringBuffer buffer = new StringBuffer();
		for(int i = 0;i<this.clzPaths.size();i++)
		{
			buffer.append(this.clzPaths.get(i));
			if(i<this.clzPaths.size()-1){
				buffer.append(";");
			}
			
			
		}
		return buffer.toString();
	}
	public String getClassPathV1(){
		return StringUtils.join(this.clzPaths,";");
	}
*/
	public ClassFile loadClass(String className) throws IOException {
		//变成字节数组
		byte[] codes = this.readBinaryCode(className);
		
		ClassFileParser parser = new ClassFileParser();
		//把字节数组传给解析类
		return parser.parse(codes);
		
	}

}
