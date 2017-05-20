package com.coderising.jvm.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		//找到文件的具体路径
		if(clzPaths.size() == 0 || className == "")
		{
			return null;
		}

		String []path = className.split("\\.");
		String MainPath = "";
		String FileName = "\\";

		for (int i = 0; i < path.length - 1; i++)
		{
			FileName += path[i] + "\\";
		}
		FileName += path[path.length - 1] + ".class";
		System.out.println(FileName);

		for (String item: clzPaths
			 ) {
			if (new File(item + FileName).exists())
			{
				MainPath += item;
				break;
			}
		}

		if (MainPath == "")
		{
			return null;
		}

		File file = new File(MainPath + FileName);
		InputStream is = null;
		byte[] buffer = new byte[(int)file.length()];
		try
		{
			System.out.println(MainPath + FileName);
			is = new FileInputStream(MainPath + FileName);
			// read stream data into buffer
			is.read(buffer);

			return buffer;



		}
		catch(Exception e) {

			// if any I/O error occurs
			e.printStackTrace();
			return null;
		} finally {

			// releases system resources associated with this stream
			if(is!=null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	private byte[] loadClassFile(String clzFileName) {
		
		return null;
	}
	
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
		
	}
	
	public String getClassPath_V1(){
		
		return  null;
	}
	
	public String getClassPath(){
		if (clzPaths.size() == 0)
		{
			return "";
		}

		String ClassPath = "";
		for (String item:
			 clzPaths) {
			ClassPath += item + ";";
		}
		ClassPath = ClassPath.substring(0, ClassPath.length() - 1); //干掉最后一个 ;

		return ClassPath;
	}

	

	

}
