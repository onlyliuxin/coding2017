package com.coderising.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {
		className = className.replace(".", File.separator) + ".class";
		for (String path : clzPaths) {
			String clzFileName = path + File.separator + className;
			byte[] codes = this.loadClassFile(clzFileName);
			if (codes != null) {
				return codes;
			}
		}
		return null;
	}

	private byte[] loadClassFile(String clzFileName){
		try {
			return IOUtils.toByteArray(new FileInputStream(clzFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}

	public String getClassPath() {
		return StringUtils.join(clzPaths, ";");
	}

	public String getClassPath_V1() {
		StringBuffer sb = new StringBuffer();
		/*
		 * for (int i = 0; i < clzPaths.size(); i++)
		 { 
		 	if (i == clzPaths.size() - 1) { 
		 	sb.append(clzPaths.get(i));
		  	break; 
		 }
		 sb.append(clzPaths.get(i));
	     sb.append(";");
		 * 
		 * }
		 */
		for (int i = 0; i < clzPaths.size(); i++) {
			sb.append(clzPaths.get(i));
			if (i < clzPaths.size() - 1) {
				sb.append(";");
			}
		}
		return sb.toString();
	}

}
