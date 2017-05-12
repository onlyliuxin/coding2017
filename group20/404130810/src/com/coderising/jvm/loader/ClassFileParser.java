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
		
		ByteCodeIterator iterator = new ByteCodeIterator(codes);
		// Magic Number
		String magicNumber = iterator.next4BytesToString();
		clzFile.setMagicNumer(magicNumber);
		// Version Number
		int minorVersion = iterator.next2BytesToInt();
		int majorVersion = iterator.next2BytesToInt();
		clzFile.setMinorVersion(minorVersion);
		clzFile.setMajorVersion(majorVersion);
		
		clzFile.setPool(parseConstantPool(iterator));
		clzFile.setAccessFlag(parseAccessFlag(iterator));
		clzFile.setClassIndex(parseClassIndex(iterator));
		
		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iterator) {
		int flagValue = iterator.next2BytesToInt();
		AccessFlag accessFlag = new AccessFlag(flagValue);
		return accessFlag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iterator) {

		ClassIndex classIndex = new ClassIndex();
		int thisClassIndex = iterator.next2BytesToInt();
		int superClassIndex = iterator.next2BytesToInt();
		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(superClassIndex);

		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iterator) {
		ConstantPool pool = new ConstantPool();
		int ConstantNumber = iterator.next2BytesToInt();
		pool.addConstantInfo(new NullConstantInfo());

		for (int i = 1; i <= ConstantNumber - 1; i++) {
			int tag = iterator.nextByteToInt();

			if (tag == 7) {
				ClassInfo clzInfo = new ClassInfo(pool);
				int utf8Index = iterator.next2BytesToInt();
				clzInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(clzInfo);
			}
			else if(tag == 1){
				UTF8Info utf8Info = new UTF8Info(pool);
				int length = iterator.next2BytesToInt();
				utf8Info.setLength(length);
				byte[] utf8Bytes = iterator.getBytes(length);
				String value = null;
				try {
					value = new String(utf8Bytes, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				utf8Info.setValue(value);
				pool.addConstantInfo(utf8Info);
			}
			else if(tag == 12){
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);

				int index_Name = iterator.next2BytesToInt();
				int index_Describe = iterator.next2BytesToInt();
				
				nameAndTypeInfo.setIndex_Name(index_Name);
				nameAndTypeInfo.setIndex_Describe(index_Describe);
				pool.addConstantInfo(nameAndTypeInfo);	
			}
			
			else if(tag == 10){
				MethodRefInfo methofInfo = new MethodRefInfo(pool);
				int index_ClassInfo = iterator.next2BytesToInt();
				int index_NameAndType = iterator.next2BytesToInt();
				
				methofInfo.setClassInfoIndex(index_ClassInfo);
				methofInfo.setNameAndTypeIndex(index_NameAndType);
				pool.addConstantInfo(methofInfo);
				
			}
			
			else if (tag == 9) {
				
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				int index_ClassInfo = iterator.next2BytesToInt();
				int index_NameAndType = iterator.next2BytesToInt();
				
				fieldRefInfo.setClassInfoIndex(index_ClassInfo);
				fieldRefInfo.setNameAndTypeIndex(index_NameAndType);
				pool.addConstantInfo(fieldRefInfo);
			}
			else if (tag == 8) {
				
				StringInfo stringInfo = new StringInfo(pool);
				int index = iterator.next2BytesToInt();
				
				stringInfo.setIndex(index);
				pool.addConstantInfo(stringInfo);
			}
		}
		return pool;
	}

	
}
