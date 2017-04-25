package com.coding.basic.homework_04.jvm.clz;

import java.io.UnsupportedEncodingException;

import com.coding.basic.homework_04.jvm.attr.AccessFlag;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;
import com.coding.basic.homework_04.jvm.field.Field;
import com.coding.basic.homework_04.jvm.info.ClassInfo;
import com.coding.basic.homework_04.jvm.info.FieldRefInfo;
import com.coding.basic.homework_04.jvm.info.MethodRefInfo;
import com.coding.basic.homework_04.jvm.info.NameAndTypeInfo;
import com.coding.basic.homework_04.jvm.info.NullConstantInfo;
import com.coding.basic.homework_04.jvm.info.StringInfo;
import com.coding.basic.homework_04.jvm.info.UTF8Info;
import com.coding.basic.homework_04.jvm.method.Method;
import com.coding.basic.homework_04.jvm.util.ByteCodeIterator;

public class ClassFileParser {

	private ClassFile clzFile;
	
	private ConstantPool pool;
	
	public ClassFile parser(byte[] codes) {
		ByteCodeIterator iterator = new ByteCodeIterator(codes);
		clzFile = new ClassFile();
		clzFile.setMagicNumber(iterator.nextU4ToHexString());
		clzFile.setMinorVersion(iterator.nextU2ToInt());
		clzFile.setMajorVersion(iterator.nextU2ToInt());
		clzFile.setConstantNum(iterator.nextU2ToInt());
		
		

		clzFile.setConstantPool(parserConstantPool(iterator));
	

		clzFile.setAccessFlag(new AccessFlag(iterator.nextU2ToInt()));
		
		parseClzIndex(clzFile, iterator);
		
		parseInterface(iterator);
		
		parseField(clzFile, iterator);
		
		parseMethod(clzFile, iterator);
		
		return clzFile;
	}

	private void parseClzIndex(ClassFile clzFile, ByteCodeIterator iterator) {
		ClassIndex clzIndex = new ClassIndex();
		clzIndex.setThisClassIndex(iterator.nextU2ToInt());
		clzIndex.setSuperClassIndex(iterator.nextU2ToInt());
		
		clzFile.setClzIndex(clzIndex);
		
	}

	private void parseMethod(ClassFile clzFile, ByteCodeIterator iterator) {
		int methodCount = iterator.nextU2ToInt();
		for(int i=0; i<methodCount; i++){
			Method method = Method.parse(clzFile, iterator);
			clzFile.addMethod(method);
		}
	}

	private void parseField(ClassFile clzFile, ByteCodeIterator iterator) {
		int fieldCount = iterator.nextU2ToInt();
		for(int i=0; i<fieldCount; i++){
			Field field = Field.parse(clzFile.getConstantPool(), iterator);
			clzFile.addField(field);
		}
	}

	private void parseInterface(ByteCodeIterator iterator) {
		int count = iterator.nextU2ToInt();
		//TODO 如果类中有接口，这里需要解析
	}

	private ConstantPool parserConstantPool(ByteCodeIterator iterator){
		pool = new ConstantPool();
		
		
		pool.addConstantInfo(new NullConstantInfo());
		for(int i=1; i <= clzFile.getConstantNum() - 1; i++){
			int tag = iterator.nextU1toInt();
			
			if(tag == 7){ //class info
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(iterator.nextU2ToInt());
				pool.addConstantInfo(clzInfo);
			}else if(tag == 1){ //utf8 info
				int len = iterator.nextU2ToInt();
				byte[] data = iterator.getBytes(len);
				String value = null;
				try {
					value = new String(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				UTF8Info utf8Str = new UTF8Info(pool);
				utf8Str.setLength(len);
				utf8Str.setValue(value);
				pool.addConstantInfo(utf8Str);
			}else if(tag == 8){ //String info
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setString_index(iterator.nextU2ToInt());
				pool.addConstantInfo(stringInfo);
			}else if(tag == 9){ //FieldRef info
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				fieldRefInfo.setClz_index(iterator.nextU2ToInt());
				fieldRefInfo.setNameAndType_index(iterator.nextU2ToInt());
				pool.addConstantInfo(fieldRefInfo);
			}else if(tag == 10){ //MethodRef info
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				methodRefInfo.setClass_index(iterator.nextU2ToInt());
				methodRefInfo.setNameAndType_index(iterator.nextU2ToInt());
				pool.addConstantInfo(methodRefInfo);
			}else if(tag == 12){ //NameAndType info
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				nameAndTypeInfo.setIndex1(iterator.nextU2ToInt());
				nameAndTypeInfo.setIndex2(iterator.nextU2ToInt());
				pool.addConstantInfo(nameAndTypeInfo);
			}else{
				throw new RuntimeException("this tag" + tag+"has no already implement yet!!");
			}
		}
		
		System.out.println("finished parser constantPool!!");
		return pool;
	}
	
}
