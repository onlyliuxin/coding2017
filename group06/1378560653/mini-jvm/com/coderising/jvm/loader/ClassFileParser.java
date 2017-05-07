package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.AccessFlag;
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
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;
import com.sun.xml.internal.bind.v2.runtime.Name;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		
		ClassFile clzFile = new ClassFile();
		
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		
		//1.magicNumber
		String magicNumber = iter.nextU4ToHexString();
	
		if(!magicNumber.equals("cafebabe")){
			return null;
		}
		
		//2.Version Number
		clzFile.setMinorVersion(iter.nextU2ToInt());
		clzFile.setMajorVersion(iter.nextU2ToInt());
		
		//3.ConstantPool
		ConstantPool pool = parseConstantPool(iter);
		clzFile.setConstPool(pool);
		
		//4.AcessFlag
		AccessFlag flag = parseAccessFlag(iter);
		clzFile.setAccessFlag(flag);
		
		//5.ClassIndex
		ClassIndex clzIndex = parseClassIndex(iter);
		clzFile.setClassIndex(clzIndex);
		
		//6.Interface
		parseInterfaces(iter);
		
		//7.Filed
		parseFileds(clzFile, iter);
		
		//8.Method
		parseMethods(clzFile, iter);
		
		return clzFile;
	}
	
	/*
	 * ---------------------------------------------------------------------------
	 * 4.Interface
	 * 5.Field
	 * 6.Method
	 */
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();
		System.out.println("interfaceCount:" + interfaceCount);
		
		//TODO:EmployeeV1.java没有实现接口，只读出其接口个数：0;若实现interface,则解析
	}
	
	private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
		int fieldCount = iter.nextU2ToInt();
		System.out.println("fieldCount:" + fieldCount);
		
		for(int i = 1; i <= fieldCount; i++){
			Field field = Field.parse(clzFile, iter);
			clzFile.addField(field);
		}
		
	}
	
	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {
		int methodCount = iter.nextU2ToInt();
		System.out.println("methodCount:" + methodCount);
		
		for(int i = 1; i <= methodCount; i++){
			Method method = Method.parse(clzFile, iter);
			clzFile.addMethod(method);
		}
		
	}
	
	/*
	 * ---------------------------------------------------------------------------
	 * 1.ConstantPool
	 * 2.AccessFlag
	 * 3.ClassIndex
	 */

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		
		int constantpPoolSize = iter.nextU2ToInt();
		System.out.println("Constant Pool Size :" + constantpPoolSize);
		
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());//注意这里
		
		for(int i = 1; i < constantpPoolSize; i++){
			int tag = iter.nextU1ToInt();
			
			if(tag == 7) {
				//ClassInfo
				int utf8Index = iter.nextU2ToInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);
				
				pool.addConstantInfo(clzInfo);
			} else if (tag == 1){
				//UTF8Info
				int length = iter.nextU2ToInt();
				byte[] data = iter.getBytes(length);
				String value = null;
				try {
					value = new String(data,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(length);
				utf8Info.setValue(value);
				
				pool.addConstantInfo(utf8Info);	
			} else if (tag == 8) {
				//StringInfo
				int index = iter.nextU2ToInt();
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setIndex(index);
				
				pool.addConstantInfo(stringInfo);
			} else if (tag == 9) {
				//FiledRefInfo
				int classInfoIndex = iter.nextU2ToInt();
				int nameAndTypeIndex = iter.nextU2ToInt();
				
				FieldRefInfo field = new FieldRefInfo(pool);
				field.setClassInfoIndex(classInfoIndex);
				field.setNameAndTypeIndex(nameAndTypeIndex);
				
				pool.addConstantInfo(field);
			} else if (tag == 10){
				//MethodRefInfo
				int classInfoIndex = iter.nextU2ToInt();
				int nameAndTypeIndex = iter.nextU2ToInt();
				
				MethodRefInfo method = new MethodRefInfo(pool);
				method.setClassInfoIndex(classInfoIndex);
				method.setNameAndTypeIndex(nameAndTypeIndex);
				
				pool.addConstantInfo(method);
			} else if(tag == 12){
				//NameAndTypeInfo
				int name_index = iter.nextU2ToInt();
				int descriptor_index = iter.nextU2ToInt();
				
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				nameAndTypeInfo.setIndex1(name_index);
				nameAndTypeInfo.setIndex2(descriptor_index);
				
				pool.addConstantInfo(nameAndTypeInfo);
			} else {
				throw new RuntimeException("the constant pool tag " + tag + "has not been implemented yet");
			}
		}
		return pool;
	}
	
	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag flag = new AccessFlag(iter.nextU2ToInt());
		return flag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		ClassIndex clzIndex = new ClassIndex();
		clzIndex.setThisClassIndex(iter.nextU2ToInt());
		clzIndex.setSuperClassIndex(iter.nextU2ToInt());
		return clzIndex;
	}

	

	
}
