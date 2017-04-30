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

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile classFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magicNumber = iter.nextU4ToHexString();
		if(!magicNumber.equals("cafebabe")){
			return null;
		}
		classFile.setMinorVersion(iter.nextU2ToInt());
		classFile.setMajorVersion(iter.nextU2ToInt());
		
		ConstantPool pool = parseConstantPool(iter);
		classFile.setConstPool(pool);
		
		AccessFlag accessFlag = parseAccessFlag(iter);
		classFile.setAccessFlag(accessFlag);
		
		ClassIndex classIndex = parseClassIndex(iter);
		classFile.setClassIndex(classIndex);
		
		parseInterfaces(iter);
		parseFileds(classFile, iter);
		parseMethods(classFile, iter);
		return classFile;
	}
	
	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		int value = iter.nextU2ToInt();
		AccessFlag accessFlag = new AccessFlag(value);
		accessFlag.setFlagValue(value);
		return accessFlag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		int thisClassIndex = iter.nextU2ToInt();
		int superClassIndex = iter.nextU2ToInt();
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(superClassIndex);
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constantPoolCount = iter.nextU2ToInt();
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		for (int i = 0; i < constantPoolCount-1; i++) {
			int tag = iter.nextU1toInt();
			if(tag == 7){
				int utf8Index = iter.nextU2ToInt();
				ClassInfo clzInfo= new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(clzInfo);
			}else if(tag == 1){
				int length = iter.nextU2ToInt();
				byte data[] = iter.getBytes(length);
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
			}else if(tag == 12){
				int NameIndex = iter.nextU2ToInt();
				int TypeIndex = iter.nextU2ToInt();
				NameAndTypeInfo nameAndType = new NameAndTypeInfo(pool);
				nameAndType.setIndex1(NameIndex);
				nameAndType.setIndex2(TypeIndex);
				pool.addConstantInfo(nameAndType);
			}else if(tag == 9){
				int classInfoIndex = iter.nextU2ToInt();
				int nameAndTypeIndex = iter.nextU2ToInt();
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				fieldRefInfo.setClassInfoIndex(classInfoIndex);
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				pool.addConstantInfo(fieldRefInfo);
			}else if(tag == 10){
				int classInfoIndex = iter.nextU2ToInt();
				int nameAndTypeIndex = iter.nextU2ToInt();
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				methodRefInfo.setClassInfoIndex(classInfoIndex);
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				pool.addConstantInfo(methodRefInfo);
			}else if(tag == 8){
				int stringIndex = iter.nextU2ToInt();
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setIndex(stringIndex);
				pool.addConstantInfo(stringInfo);
			}else{
				throw new RuntimeException("the constant pool tag" + tag + "has not been implemented yet");
			}
				
		}
		return pool;
	}
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();
		System.out.println("interfaceCount:" + interfaceCount);

	}

	private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
		int fieldCount = iter.nextU2ToInt();
		for (int i = 1; i <= fieldCount; i++) {
			Field field = Field.parse(clzFile.getConstantPool(), iter);
			clzFile.addField(field);
		}
	}

	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {

		int methodsCount = iter.nextU2ToInt();
		for (int i = 1; i <= methodsCount; i++) {
			Method method = Method.parse(clzFile, iter);
			clzFile.addMethod(method);
		}
	}
	
}
