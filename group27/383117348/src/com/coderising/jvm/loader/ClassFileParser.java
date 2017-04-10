package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.FloatInfo;
import com.coderising.jvm.constant.IntegerInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ClassFileParser {
	
	
	public ClassFile parse(byte[] codes) {
		ByteCodeIterator by = new ByteCodeIterator(codes);
		ClassFile file = new ClassFile();
		String magicNum = by.nextU4HexString();
		if(!magicNum.equals("cafebabe")){
			throw new RuntimeException("文件类型错误");
		}
		
		int minVersion = by.nextU2Int();
		int majorVersion = by.nextU2Int();
		
		ConstantPool constant = parseConstantPool(by);
		AccessFlag flag = parseAccessFlag(by);
		ClassIndex index = parseClassIndex(by);
		
		file.setMinorVersion(minVersion);
		file.setMajorVersion(majorVersion);
		file.setAccessFlag(flag);
		file.setClassIndex(index);
		file.setConstPool(constant);
		return file;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag flag = new AccessFlag(iter.nextU2Int());
		return flag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		ClassIndex index = new ClassIndex();
		index.setThisClassIndex(iter.nextU2Int());
		index.setSuperClassIndex(iter.nextU2Int());
		return index;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		    int constantCount = iter.nextU2Int();
	        ConstantPool pool = new ConstantPool();
	        pool.addConstantInfo(new NullConstantInfo());
	        for(int i=1;i<constantCount;i++){
	        	int tag = iter.nextU1Int();
	        	if(tag == 1){
	        		UTF8Info utf8 = new UTF8Info(pool);
	        		int length = iter.nextU2Int();
	        		try {
						utf8.setValue(new String(iter.getByteByLength(length),"utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
	        		pool.addConstantInfo(utf8);
	        	}else if(tag == 3){
	        		IntegerInfo integer = new IntegerInfo(pool);
	        		integer.setValue(iter.nextU4Integer());
	        		pool.addConstantInfo(integer);
	        	}else if(tag == 4){
	        		FloatInfo floatInfo = new FloatInfo(pool);
	        		floatInfo.setValue(iter.nextU4Float());
	        		pool.addConstantInfo(floatInfo);
	        	}else if(tag == 7){
	        		ClassInfo classInfo = new ClassInfo(pool);
	                classInfo.setUtf8Index(iter.nextU2Int());
	                pool.addConstantInfo(classInfo);
	        	}else if(tag == 8){
	        		 StringInfo stringInfo = new StringInfo(pool);
	                 stringInfo.setIndex(iter.nextU2Int());
	                 pool.addConstantInfo(stringInfo);
	        	}else if(tag ==9){
	        		 FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
	                 fieldRefInfo.setClassInfoIndex(iter.nextU2Int());
	                 fieldRefInfo.setNameAndTypeIndex(iter.nextU2Int());
	                 pool.addConstantInfo(fieldRefInfo);
	        	}else if(tag == 10){
	        		 MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
	                 methodRefInfo.setClassInfoIndex(iter.nextU2Int());
	                 methodRefInfo.setNameAndTypeIndex(iter.nextU2Int());
	                 pool.addConstantInfo(methodRefInfo);
	        	}else if(tag == 12){
	        		NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
	                nameAndTypeInfo.setIndex1(iter.nextU2Int());
	                nameAndTypeInfo.setIndex2(iter.nextU2Int());
	                pool.addConstantInfo(nameAndTypeInfo);
	        	}
	        }
	        return pool;
	}

	
}
