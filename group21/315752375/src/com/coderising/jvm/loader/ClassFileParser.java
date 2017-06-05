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
		ClassFile classFile=new ClassFile();
		ByteCodeIterator byteCodeIterator=new ByteCodeIterator(codes);
		if(!"cafebabe".equals(byteCodeIterator.nextU4ToHexString()))
			return null;
		
		classFile.setMinorVersion(byteCodeIterator.nextU2ToInt());
		classFile.setMajorVersion(byteCodeIterator.nextU2ToInt());
		
		ConstantPool constantPool=parseConstantPool(byteCodeIterator);
		classFile.setConstPool(constantPool);
		
		AccessFlag accessFlag=parseAccessFlag(byteCodeIterator);
		classFile.setAccessFlag(accessFlag);
		
		ClassIndex classIndex=parseClassInfex(byteCodeIterator);
		classFile.setClassIndex(classIndex);
		
		parseInterfaces(byteCodeIterator);
		parseFields(classFile, byteCodeIterator);
		parseMethods(classFile,byteCodeIterator);
  
		return classFile;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
	
		ConstantPool constantPool=new ConstantPool();
		int constantCount=iter.nextU2ToInt();
		constantPool.addConstantInfo(new NullConstantInfo());
		
		for(int i=1;i<constantCount;i++){
			int tag=iter.nextU1ToInt();
			if(tag==1){
				int len=iter.nextU2ToInt();
				byte[]tmp=iter.getBytes(len);
				String value=null;
				try {
					value=new String(tmp,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UTF8Info utf8Info=new UTF8Info(constantPool);
				utf8Info.setLength(len);
				utf8Info.setValue(value);
				constantPool.addConstantInfo(utf8Info);
			}else if(tag==7){
				int utf8Index=iter.nextU2ToInt();
				ClassInfo classInfo=new ClassInfo(constantPool);
				classInfo.setUtf8Index(utf8Index);
				constantPool.addConstantInfo(classInfo);
			}else if(tag==8){
				int index=iter.nextU2ToInt();
				StringInfo stringInfo=new StringInfo(constantPool);
				stringInfo.setIndex(index);
				constantPool.addConstantInfo(stringInfo);
			}else if(tag==9){
				int classInfoIndex=iter.nextU2ToInt();
				int nameAndTypeIndex=iter.nextU2ToInt();
				FieldRefInfo fieldRefInfo=new FieldRefInfo(constantPool);
				fieldRefInfo.setClassInfoIndex(classInfoIndex);
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				constantPool.addConstantInfo(fieldRefInfo);
			}else if(tag==10){
				int classInfoIndex=iter.nextU2ToInt();
				int nameAndTypeIndex=iter.nextU2ToInt();
				MethodRefInfo methodRefInfo=new MethodRefInfo(constantPool);
				methodRefInfo.setClassInfoIndex(classInfoIndex);
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				constantPool.addConstantInfo(methodRefInfo);
			}else if(tag==12){
				int index1=iter.nextU2ToInt();
				int index2=iter.nextU2ToInt();
				NameAndTypeInfo nameAndTypeInfo=new NameAndTypeInfo(constantPool);
				nameAndTypeInfo.setIndex1(index1);
				nameAndTypeInfo.setIndex2(index2);
				constantPool.addConstantInfo(nameAndTypeInfo);
			}else {
				throw new RuntimeException("this constant pool tag: "+tag+" has not implemented yet");
			}
		}
		return constantPool;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

		int value=iter.nextU2ToInt();
		AccessFlag accessFlag=new AccessFlag(value);
		return accessFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		int thisClassIndex=iter.nextU2ToInt();
		int superClassIndex=iter.nextU2ToInt();
		ClassIndex classIndex =new ClassIndex();
		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(superClassIndex);
		return classIndex;

	}
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();

		System.out.println("interfaceCount:" + interfaceCount);
		// TODO : 如果实现了interface, 这里需要解析
	}
	private void parseFields(ClassFile classFile,ByteCodeIterator iter){
		int fieldsCount=iter.nextU2ToInt();
		for(int i=0;i<fieldsCount;i++){
			Field field=Field.parse(classFile.getConstantPool(), iter);
			classFile.addField(field);
		}
	}

	private void parseMethods(ClassFile classFile,
			ByteCodeIterator iter) {
		int methodsCount=iter.nextU2ToInt();
		for(int i=0;i<methodsCount;i++){
			Method method=Method.parse(classFile, iter);
			classFile.addMethod(method);
		}
		
	}
	
}
