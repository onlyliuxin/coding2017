package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runners.Parameterized.Parameters;

import com.coderising.jvm.clz.ClassFile;

public class ClassFileLoader {
	private List<String> clzPaths = new ArrayList<String>();
	int countForClassPath = 0;
	int countForReadBinaryCode = 0;
	byte [] a = new byte[10000];
	
	/*	从指定路径读取二进制文件流，并将其保存到一个字节数组中，并返回
	 * 	@Parameters 指定路径
	 *  @字节数组
	 */
	public byte[] readBinaryCode(String className) throws IOException{
		DataInputStream dis = new DataInputStream(
				new BufferedInputStream(new FileInputStream(className)));
		for(int i = 0; dis.available() != 0; i++){
			a[i] = dis.readByte();
			countForReadBinaryCode++;
		}	
		byte []target = new byte[countForReadBinaryCode];
		System.arraycopy(a, 0, target, 0, countForReadBinaryCode);
		dis.close();
		return target;
	}
	
	public void addClassPath(String path){
		clzPaths.add(path);
		countForClassPath++;
	}
	
	public String getClassPath(){
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < countForClassPath; i++ ){
			if(i==countForClassPath-1){
				buffer.append(clzPaths.get(i));
			}else{
				buffer.append(clzPaths.get(i)+";");
			}
		}
		return buffer.toString();
	}
	
	public ClassFile loadClass(String className) throws IOException{
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
	}
	
	
	
	
	
	
	
	
	
	
}
