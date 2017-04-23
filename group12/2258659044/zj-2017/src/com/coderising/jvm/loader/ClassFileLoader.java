package com.coderising.jvm.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.exception.NotAClassFileException;
import com.coderising.jvm.util.Util;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		
		File clzFile = Util.getClzFile(clzPaths,className);
        		
		return Util.readClz(clzFile);	
				
	}
		
	public void addClassPath(String path) {
		
		this.clzPaths.add(path);
	}
			
	public String getClassPath(){
		
		StringBuffer buff = new StringBuffer();
		for (String str : clzPaths) {
			buff.append(str+";");
		}		
		return buff.substring(0, buff.length()-1);
	}

	public ClassFile loadClass(String className) throws NotAClassFileException  {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
		
	}
}
