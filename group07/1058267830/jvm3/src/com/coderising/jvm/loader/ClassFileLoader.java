package com.coderising.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

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
		
		
		int j = 0; // 用于识别是第几次进入某个分支的
		
		/**
		 * 解析常量池
		 */
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
		
		
		/**
		 * 常量池解析完了，下面解析Field属性
		 */
		
		this.startIndex += 8;// 跳过中间的8个字节
		
		int fieldsCount = getIntValueFromBytes(getNextBytes(source, 2));
		System.out.println("fieldsCount = "+fieldsCount);
		
		for(int i=0; i<fieldsCount; i++) {
			System.out.println("============================");
			int accessFlag = getIntValueFromBytes(getNextBytes(source, 2));
			int name_index = getIntValueFromBytes(getNextBytes(source, 2));
			int desc_index = getIntValueFromBytes(getNextBytes(source, 2));
			int attribute_count = getIntValueFromBytes(getNextBytes(source, 2));
			
			Field field = new Field(accessFlag, name_index, desc_index, pool);
			clzFile.addField(field);
//			System.out.println("accessFlag = "+accessFlag);
//			System.out.println("name_index = "+name_index);
//			System.out.println("desc_index = "+desc_index);
//			System.out.println("attribute_count = "+attribute_count);
		}
		
		/**
		 * Field属性解析完了，下面解析method属性
		 */
		int methodsCount = getIntValueFromBytes(getNextBytes(source, 2));
		System.out.println("methodsCount = "+ methodsCount);
		
		
		
		for(int i=0; i<methodsCount; i++) {
			int access_flag = getIntValueFromBytes(getNextBytes(source, 2));
			int name_index = getIntValueFromBytes(getNextBytes(source, 2));
			int desc_index = getIntValueFromBytes(getNextBytes(source, 2));
			int attribute_count = getIntValueFromBytes(getNextBytes(source, 2));
			System.out.println("access_Flag = "+access_flag);
			System.out.println("name_index = "+name_index);
			System.out.println("desc_index = "+desc_index);
			System.out.println("attribute_count = "+attribute_count);
			
			Method method = new Method(clzFile, access_flag, name_index, desc_index);
			clzFile.addMethod(method);
			
			System.out.println("=================================");
			
			//解析method属性中的code属性
			int value = getIntValueFromBytes(getNextBytes(source, 2));
			System.out.println("value = "+value);
			if(value == 11) {
				int attrLen = getIntValueFromBytes(getNextBytes(source, 4));
				int maxStack = getIntValueFromBytes(getNextBytes(source, 2));
				int maxLocals = getIntValueFromBytes(getNextBytes(source, 2));
				int codeLen = getIntValueFromBytes(getNextBytes(source, 4));
				String code = getCodeFromBytes(getNextBytes(source, codeLen));
				String s = "";
				int k = 0;
				ByteCodeCommand[] cmds = new ByteCodeCommand[code.length()/2];
				
				for(int m=0; m<code.length(); m=m+2) {
					s = code.charAt(m) + code.charAt(m+1) + "";
					ByteCodeCommand byteCodeCommand = new ByteCodeCommand(clzFile, s) {
						
						@Override
						public String toString(ConstantPool pool) {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public int getLength() {
							// TODO Auto-generated method stub
							return 0;
						}
					};
					
					cmds[k++] = byteCodeCommand;
					
				}
				
				CodeAttr codeAttr = new CodeAttr(value, attrLen, maxStack, maxLocals, codeLen, code, cmds);
				// 解析完了每个method属性中的code属性
				method.setCodeAttr(codeAttr);
				
				//跳过异常表的长度属性和code的两个子属性
				int excepTableLength = getIntValueFromBytes(getNextBytes(source, 2));
				int childCodeCount = getIntValueFromBytes(getNextBytes(source, 2));
				
				getIntValueFromBytes(getNextBytes(source, 2));// 跳过LineNumberTable tag值
				int lineNumberTableLength = getIntValueFromBytes(getNextBytes(source, 4));
				this.startIndex += lineNumberTableLength; //跳过LineNumberTable的字节
				this.startIndex += 2;
				int localVariableTableLength = getIntValueFromBytes(getNextBytes(source, 4));
				this.startIndex += localVariableTableLength; //跳过LocalVariableTable的字节
				
				System.out.println("attrLen = "+ attrLen);
				System.out.println("maxStack = "+ maxStack);
				System.out.println("maxLocals = "+ maxLocals);
				System.out.println("codeLen = "+ codeLen);
				System.out.println("code = " + code);
				System.out.println("excepTableLength = " + excepTableLength);
				System.out.println("childCodeCount = " + childCodeCount);
				System.out.println("lineNumberTableLength = " + lineNumberTableLength);
				System.out.println("localVariableTableLength = " + localVariableTableLength);
				
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
	
	private String getCodeFromBytes(byte[] data) {
		return Hex.encodeHexString(data);
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
