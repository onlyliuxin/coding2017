package com.coderising.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ClassFileLoader {
	
	private List<String> clzPaths = new ArrayList<String>();
	
	private int startIndex = 4;
	
	public byte[] readBinaryCode(String className) {
		String fileName = this.getClassPath() + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
		return loadClassFile(fileName);
	}
	
	
	public ClassFile loadClass(String className) {
		ClassFile clzFile = new ClassFile() ;
		byte[] source = readBinaryCode(className);
		int minorVersion = getIntValueFromBytes(getNextBytes(source, 2)); 
		int majorVersion = getIntValueFromBytes(getNextBytes(source, 2)); 
		clzFile.setMinorVersion(minorVersion);
		clzFile.setMajorVersion(majorVersion);
		
		int constantPoolSize = getIntValueFromBytes(getNextBytes(source, 2)) - 1;
		System.out.println("poolsize = "+constantPoolSize);
		System.out.println("=======================");
		ConstantPool pool = new ConstantPool();
		NullConstantInfo nullConstantInfo = new NullConstantInfo();
		pool.addConstantInfo(nullConstantInfo);
		pool.setSize(constantPoolSize);
		
		ClassIndex clzIndex = new ClassIndex();
		
		int j = 0;
		
		
		for(int i = 0; i<constantPoolSize; i++) {
			int value = getIntValueFromBytes(getNextBytes(source, 1));
			
			if(value == 7) {
				ClassInfo clzInfo = new ClassInfo(pool);
				int index = getIntValueFromBytes(getNextBytes(source, 2));
				clzInfo.setUtf8Index(index);
				pool.addConstantInfo(clzInfo);
				System.out.println(i+1 + "  "+ "index = "+index);
			}
			else if(value == 1){
				j++; // 用于识别是第几次进入这个分支
				UTF8Info utf8Info = new UTF8Info(pool);
				int length = getIntValueFromBytes(getNextBytes(source, 2));
				String utf8Value = getStringValueFromBytes(getNextBytes(source, length));
				utf8Info.setValue(utf8Value);
				pool.addConstantInfo(utf8Info);
				System.out.println(i+1 + "  "+ "StringVlue = "+utf8Value);
				
				if(j == 1) {
					clzIndex.setThisClassIndex(i);
					clzFile.setClassIndex(clzIndex);
				}
				
				if(j == 2) {
					clzIndex.setSuperClassIndex(i);
					clzFile.setClassIndex(clzIndex);
				}
				
			}
			else if(value == 10) {
				MethodRefInfo methodRef = new MethodRefInfo(pool);
				int index = getIntValueFromBytes(getNextBytes(source, 2));
				int nameAndTypeIndex = getIntValueFromBytes(getNextBytes(source, 2));
				methodRef.setClassInfoIndex(index);
				methodRef.setNameAndTypeIndex(nameAndTypeIndex);
				pool.addConstantInfo(methodRef);
				System.out.println(i+1 + "  "+ "ClassInfoIndex = "+index + "    " + "nameAndTypeIndex=" + nameAndTypeIndex);
			}
			else if(value == 12) {
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				int name_index = getIntValueFromBytes(getNextBytes(source, 2));
				int descriptor_index = getIntValueFromBytes(getNextBytes(source, 2));
				nameAndTypeInfo.setIndex1(name_index);
				nameAndTypeInfo.setIndex2(descriptor_index);
				pool.addConstantInfo(nameAndTypeInfo);
				System.out.println(i+1 + "  "+ "name_index = "+name_index + "    " + "descriptor_index=" + descriptor_index);
			}
			else if(value == 9) {
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				int classInfoIndex = getIntValueFromBytes(getNextBytes(source, 2));
				int nameAndTypeIndex = getIntValueFromBytes(getNextBytes(source, 2));
				fieldRefInfo.setClassInfoIndex(classInfoIndex);
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				pool.addConstantInfo(fieldRefInfo);
				System.out.println(i+1 + "  "+ "classInfoIndex = "+classInfoIndex + "    " + "classInfoIndex=" + classInfoIndex);
			}
			else if(value == 8) {
				StringInfo stringInfo = new StringInfo(pool);
				int index = getIntValueFromBytes(getNextBytes(source, 2));
				stringInfo.setIndex(index);
				pool.addConstantInfo(stringInfo);
				System.out.println(i+1 + "  "+ "stringInfoIndex = "+index);
			}
			else{
				System.out.println("==================================");
				System.out.println("value = " + value);
				throw new RuntimeException("该类型暂时不支持...");
			}
			
		}
		clzFile.setConstPool(pool);
		return clzFile;
	}
	
	private int getIntValueFromBytes(byte[] data) {
		String s = Hex.encodeHexString(data);
		return Integer.parseInt(s,16);
	} 
	
	private String getStringValueFromBytes(byte[] data) {
		String result = new String(data);
		return result;
	} 
	
	private byte[] getNextBytes(byte[] source, int num) {
		byte[] result = new byte[num];
		int _startIndex = startIndex;
		for(int i=0; i<num; i++) {
			result[i] = source[_startIndex++];
		}
		this.startIndex += num;
		return result;
	}
	
	private byte[] loadClassFile(String clzFileName) {
	
		File file = new File(clzFileName);
		try {
			return IOUtils.toByteArray(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	public void addClassPath(String path) {
		if(path == null || this.clzPaths.contains(path)) {
			return;
		}
		
		this.clzPaths.add(path);
	}
	
	public String getClassPath_V1() {
		
		return null;
	}
	
	public String getClassPath() {
		return StringUtils.join(this.clzPaths, ";");
	}
	
}
