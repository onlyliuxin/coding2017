package com.pan.jvm;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoaderTmp {
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
}
