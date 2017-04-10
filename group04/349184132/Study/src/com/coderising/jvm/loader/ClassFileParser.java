package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.*;

import java.io.UnsupportedEncodingException;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ClassFile clzFile = new ClassFile();

		ByteCodeIterator iter = new ByteCodeIterator(codes);

		String magicNumber = iter.nextU4ToHexString();

		if(!"cafebabe".equals(magicNumber)){
			return null;
		}

		clzFile.setMinorVersion(iter.nextU2ToInt());

		clzFile.setMajorVersion(iter.nextU2ToInt());

		ConstantPool pool = parseConstantPool(iter);
		clzFile.setConstPool(pool);

		AccessFlag flag = parseAccessFlag(iter);
		clzFile.setAccessFlag(flag);

		ClassIndex index = parseClassInfex(iter);

		clzFile.setClassIndex(index);

		

		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

		return null;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {

		return null;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constPoolCount = iter.nextU2ToInt();
		System.out.println("Constant Pool Size : " + constPoolCount);
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());

		for(int i=1;i<constPoolCount;i++){
			int tag = iter.nextU1ToInt();

			if(tag==7){
				//Class Info
				int utf8Index = iter.nextU2ToInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(clzInfo);
			}else if(tag==1){
				//UTF-8 Info
				int len = iter.nextU2ToInt();
				byte[] data = iter.getByte(len);
				String value = null;

				try {
					value = new String(data,"UTF-8");

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(len);
				utf8Info.setValue(value);
				pool.addConstantInfo(utf8Info);
			}else if(tag==8){
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(stringInfo);
			}else if(tag==9){
				FieldRefInfo fieldInfo = new FieldRefInfo(pool);
				fieldInfo.setClassInfoIndex(iter.nextU2ToInt());
				fieldInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(fieldInfo);
			}else if(tag==10){
				MethodRefInfo method = new MethodRefInfo(pool);
				method.setClassInfoIndex(iter.nextU2ToInt());
				method.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(method);
			}else if(tag==12){
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
				nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
				nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(nameAndTypeInfo);
			}else{
				throw new RuntimeException("the constant pool tag " + tag + "has not been implement");
			}
		}

		return pool;
	}

	
}
