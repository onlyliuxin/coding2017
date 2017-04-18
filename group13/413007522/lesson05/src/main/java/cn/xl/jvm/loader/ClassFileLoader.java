package cn.xl.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import cn.xl.jvm.clz.ClassFile;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();


	public byte[] readBinaryCode(String className) {

		className = className.replace('.', File.separatorChar) +".class";

		for(String path : this.clzPaths){

			String clzFileName = path + File.separatorChar + className;
			byte[] codes = loadClassFile(clzFileName);
			if(codes != null){
				return codes;
			}			
		}

		return null;
	}

	
	public ClassFile loadClass(String className){
		
		byte[] codes = readBinaryCode(className);
		
		ClassFileParser  classFileParser = new ClassFileParser();
		ClassFile classFile = classFileParser.parse(codes);
		
		return classFile;
		
	}
	
	
	

	private byte[] loadClassFile(String clzFileName){

		try {
			File f = new File(clzFileName);

			return IOUtils.toByteArray(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		/*InputStream in = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			// 一次读一个字节
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				baos.write(tempbyte);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return baos.toByteArray();	*/

	}



	public void addClassPath(String path) {
		if(this.clzPaths.contains(path)){
			return;
		}
		clzPaths.add(path);
	}



	public String getClassPath(){
		/*StringBuffer sbf = new StringBuffer();
		if(clzPaths.size() >= 1){
			sbf.append(clzPaths.get(0));
		}
		for(int i = 1; i < clzPaths.size(); i++){
			sbf.append(";");
			sbf.append(clzPaths.get(i));
		}
		return sbf.toString();
		*/

		
		return StringUtils.join(this.clzPaths, ";");

	}



}
