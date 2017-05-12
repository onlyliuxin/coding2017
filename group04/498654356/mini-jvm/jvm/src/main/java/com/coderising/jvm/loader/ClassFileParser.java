package com.coderising.jvm.loader;

import org.junit.Assert;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NULLConstantInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;


public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		if(codes == null || codes.length == 0){
			throw new RuntimeException("illegality byteCode");
		}
		ByteCodeIterator it = new ByteCodeIterator(codes);
		ClassFile clzFile = new ClassFile();
		it.skip(4);	//CAFE BABE
		clzFile.setMinorVersion(it.next2ByteToInt());
		clzFile.setMajorVersion(it.next2ByteToInt());
		
		//constant_pool
		clzFile.setConstantPool(parseConstantPool(it));
		
		//access_flag
		clzFile.setAccessFlag(parseAccessFlag(it));
		
		//classIndex
		clzFile.setClassIndex(parseClassInfex(it));
		
		//interfaces TODO 
		int interfaceCount = it.next2ByteToInt();
		Assert.assertEquals("no have ", 0, interfaceCount);
		
		//fields
		parseField(it, clzFile);
		
		//methods
		parseMethod(it, clzFile);
		
		return clzFile;
	}

	private void parseMethod(ByteCodeIterator it, ClassFile clzFile) {
		int methodCount = it.next2ByteToInt();
		while(methodCount > 0) {
			Method method = new Method();
			int accessFlag = it.next2ByteToInt();
			int nameIndex = it.next2ByteToInt();
			int descIndex = it.next2ByteToInt();
			int attrCount = it.next2ByteToInt();
			method.setAccessFlag(accessFlag);
			method.setNameIndex(nameIndex);
			method.setDescIndex(descIndex);
			method.setConstantPool(clzFile.getConstantPool());
			while(attrCount > 0) {
				int attrNameIndex = it.next2ByteToInt();
				String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
				if(AttributeInfo.ATTR_CODE.equals(attrName)) {	//code
					it.back(2);
					CodeAttr codeAttr = CodeAttr.parse(it, clzFile.getConstantPool());
					method.setCodeAttr(codeAttr);
				} else {
					throw new RuntimeException("no implements attributeName: " + attrName);
				}
				attrCount--;
			}
			clzFile.addMethod(method);
			methodCount--;
		}
	}

	private void parseField(ByteCodeIterator it, ClassFile clzFile) {
		int fieldCount = it.next2ByteToInt();
		while(fieldCount > 0) {
			Field field = new Field();
			int accessFlag = it.next2ByteToInt();
			int nameIndex = it.next2ByteToInt();
			int descIndex = it.next2ByteToInt();
			int attrCount = it.next2ByteToInt();
			Assert.assertEquals("no have ", 0, attrCount);	//TODO
			field.setAccessFlag(accessFlag);
			field.setNameIndex(nameIndex);
			field.setDescIndex(descIndex);
			field.setConstantPool(clzFile.getConstantPool());
			clzFile.addFiled(field);
			fieldCount--;
		}
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		int value = iter.next2ByteToInt();
		AccessFlag accessFlag = new AccessFlag(value);
		return accessFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		int thisClassIndex = iter.next2ByteToInt();
		int superClassIndex = iter.next2ByteToInt();
		ClassIndex classIndex = new ClassIndex(thisClassIndex, superClassIndex);
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int poolCount = iter.next2ByteToInt() - 1;
		ConstantPool constantPool = new ConstantPool();
		constantPool.setSize(poolCount);
		constantPool.addConstantInfo(new NULLConstantInfo());
		while(poolCount > 0) {
			int tag = iter.next1ByteToInt();
			if(tag == ConstantPool.C_CLASS_INFO) {
				int nameIndex = iter.next2ByteToInt();
				ClassInfo classInfo = new ClassInfo(nameIndex, constantPool);
				constantPool.addConstantInfo(classInfo);
			} else if(tag == ConstantPool.C_UTF8_INFO) {
				int length = iter.next2ByteToInt();
				String str = iter.nextLengthByteToString(length);
				UTF8Info utf8Info = new UTF8Info(str);
				constantPool.addConstantInfo(utf8Info);
			} else if(tag == ConstantPool.C_METHODREF_INFO) {
				int classInfoIndex = iter.next2ByteToInt();
				int nameAndTypeIndex = iter.next2ByteToInt();
				MethodRefInfo methodRefInfo = new MethodRefInfo(classInfoIndex, nameAndTypeIndex);
				constantPool.addConstantInfo(methodRefInfo);
			} else if(tag == ConstantPool.C_NAME_AND_TYPE_INFO) {
				int index1 = iter.next2ByteToInt();
				int index2 = iter.next2ByteToInt();
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(index1 , index2);
				constantPool.addConstantInfo(nameAndTypeInfo);
			} else if(tag == ConstantPool.C_FIELDREF_INFO) {
				int classInfoIndex = iter.next2ByteToInt();
				int nameAndTypeIndex = iter.next2ByteToInt();
				FieldRefInfo fieldRefInfo = new FieldRefInfo(classInfoIndex, nameAndTypeIndex) ;
				constantPool.addConstantInfo(fieldRefInfo);
			} else if(tag == ConstantPool.C_STRING_INFO) {
				int index = iter.next2ByteToInt();
				StringInfo stringInfo = new StringInfo(index);
				constantPool.addConstantInfo(stringInfo);
			} else {
				throw new RuntimeException("no implement tag = " + tag);
			}
			poolCount--;
		}
		return constantPool;
	}

}
