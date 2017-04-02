package com.donaldy.jvm.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	static final int BUFFER_SIZE = 1024;
	
	public byte[] readBinaryCode(String className) {



		for (String clzPath : clzPaths) {

			File file = new File(clzPath + className.replace(".", "\\") + ".class");
			

			if (!file.exists())
				continue;

			try (
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				ByteArrayOutputStream bos = new ByteArrayOutputStream()
			)
			{

				byte [] buffer = new byte[BUFFER_SIZE];

				int len;

				while ((len = bis.read(buffer, 0, BUFFER_SIZE)) > 0) {
					bos.write(buffer, 0, len);
				}

				return bos.toByteArray();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
		
	}
	
	
	public void addClassPath(String path) {
		if (path == null)
			return;

		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		int length = clzPaths.size();
		for (int i = 0 ; i < length; ++i) {
			sb.append(clzPaths.get(i));
			if (i + 1 < length)
				sb.append(";");
		}
		return sb.toString();
	}

	

	

}
