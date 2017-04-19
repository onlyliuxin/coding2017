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

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);

		String magicNumber = iter.nextU4ToHexString();
		if(!"cafebabe".equals(magicNumber)){
			return null;
		}

		clzFile.setMinorVersion(iter.nextU2toInt());
		clzFile.setMajorVersion(iter.nextU2toInt());

		ConstantPool pool = parseConstantPool(iter);
		clzFile.setConstPool(pool);

		AccessFlag flag = parseAccessFlag(iter);
		clzFile.setAccessFlag(flag);

		ClassIndex clzIndex = parseClassInfex(iter);
		clzFile.setClassIndex(clzIndex);

		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag accessFlag = new AccessFlag(iter.nextU2toInt());

		return accessFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		int thisClassIndex = iter.nextU2toInt();
		int superClassIndex = iter.nextU2toInt();
		ClassIndex clzIndex = new ClassIndex();
		clzIndex.setThisClassIndex(thisClassIndex);
		clzIndex.setSuperClassIndex(superClassIndex);
		return clzIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constantPoolCount = iter.nextU2toInt();//常量池中常量的个数
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());

		for (int i = 1; i < constantPoolCount - 1; i++) { //常量池
			int tag = iter.nextU1toInt();

			if(7 == tag){ //ClassInfo
				int utf8Index = iter.nextU2toInt(); //name_index
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);

				pool.addConstantInfo(clzInfo);
			}else if(1 == tag){ //UTF8 String
				int len = iter.nextU2toInt();
				byte[] data = iter.getBytes(len);
				String value = null;
				try {
					value = new String(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(len);
				utf8Info.setValue(value);
				pool.addConstantInfo(utf8Info);
			}else if(8 == tag){
				StringInfo strInfo = new StringInfo(pool);
				strInfo.setIndex(iter.nextU2toInt());
				pool.addConstantInfo(strInfo);
			}else if(9 == tag){
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				fieldRefInfo.setClassInfoIndex(iter.nextU2toInt());
				fieldRefInfo.setNameAndTypeIndex(iter.nextU2toInt());
				pool.addConstantInfo(fieldRefInfo);
			}else if(10 == tag){
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				methodRefInfo.setClassInfoIndex(iter.nextU2toInt());
				methodRefInfo.setNameAndTypeIndex(iter.nextU2toInt());
				pool.addConstantInfo(methodRefInfo);
			}else if(12 == tag){
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				nameAndTypeInfo.setIndex1(iter.nextU2toInt());
				nameAndTypeInfo.setIndex2(iter.nextU2toInt());
				pool.addConstantInfo(nameAndTypeInfo);
			}else {
				throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
			}
		}

		return pool;
	}

	
}
