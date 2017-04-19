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
import com.coding.basic.homework_04.jvm.util.ByteCodeIterator;

public class ClassFileParser {

	private ClassFile classFile;
	
	private ConstantPool pool;
	
	public ClassFile parser(byte[] codes) {
		ByteCodeIterator iterator = new ByteCodeIterator(codes);
		classFile = new ClassFile();
		classFile.setMagicNumber(iterator.nextU4ToHexString());
		classFile.setMinorVersion(iterator.nextU2ToInt());
		classFile.setMajorVersion(iterator.nextU2ToInt());
		classFile.setConstantNum(iterator.nextU2ToInt());
		
		System.out.println("Constant Pool Count :" + classFile.getConstantNum());
		
		try {
			classFile.setConstantPool(parserConstantPool(iterator));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		classFile.setAccessFlag(new AccessFlag(iterator.nextU2ToInt()));
		
		ClassIndex clzIndex = new ClassIndex();
		clzIndex.setThisClassIndex(iterator.nextU2ToInt());
		clzIndex.setSuperClassIndex(iterator.nextU2ToInt());
		
		classFile.setClzIndex(clzIndex);
		parseInterface(iterator);
		
		parseField(classFile, iterator);
		
		return classFile;
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
		System.out.println("interface count:" + count);
		//TODO 如果类中有接口，这里需要解析
	}

	private ConstantPool parserConstantPool(ByteCodeIterator iterator) throws UnsupportedEncodingException{
		pool = new ConstantPool();
		
		
		pool.addConstantInfo(new NullConstantInfo());
		for(int i=1; i <= classFile.getConstantNum() - 1; i++){
			int tag = iterator.nextU1toInt();
			
			if(tag == 7){ //class info
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(iterator.nextU2ToInt());
				pool.addConstantInfo(clzInfo);
			}else if(tag == 1){ //utf8 info
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(iterator.nextU2ToInt());
				String string  = iterator.nextUxToHexString(utf8Info.getLength());
				utf8Info.setValue(string);
//				System.out.println(utf8Info.toString());
				
				pool.addConstantInfo(utf8Info);
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
				nameAndTypeInfo.setClz_index(iterator.nextU2ToInt());
				nameAndTypeInfo.setDescriptor_index(iterator.nextU2ToInt());
				pool.addConstantInfo(nameAndTypeInfo);
			}else{
				throw new RuntimeException("this tag" + tag+"has no already implement yet!!");
			}
		}
		System.out.println("pool size:" +pool.getSize());
		
		System.out.println("finished parser constantPool!!");
		return pool;
	}
	
}
