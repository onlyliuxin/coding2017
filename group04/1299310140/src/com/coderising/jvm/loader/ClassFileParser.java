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
		
		ClassFile classFile = new ClassFile();
		
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		
		String magicNumber = iter.nextU4ToString();
		System.out.println("magicNumber:"+magicNumber);
		
		int minorVersion = iter.nextU2ToInt();
		int majorVersion = iter.nextU2ToInt();
		classFile.setMinorVersion(minorVersion);
		classFile.setMajorVersion(majorVersion);
		
		ConstantPool constantPool = this.parseConstantPool(iter);
		classFile.setConstPool(constantPool);
		
		AccessFlag accessFlag = this.parseAccessFlag(iter);
		classFile.setAccessFlag(accessFlag);
		
		ClassIndex classIndex = this.parseClassIndex(iter);
		classFile.setClassIndex(classIndex);
		
		return classFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter){
		AccessFlag accessFlag = new AccessFlag(iter.nextU2ToInt());
		return accessFlag;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter){
		int thisClassIndex = iter.nextU2ToInt();
		int superClassIndex = iter.nextU2ToInt();
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(superClassIndex);
		
		return classIndex;
	}

    private ConstantPool parseConstantPool(ByteCodeIterator iter){
    	
    	ConstantPool constantPool = new ConstantPool();
    	
    	constantPool.addConstantInfo(new NullConstantInfo());
    	
		int constantNumber = iter.nextU2ToInt();//常量池项的个数
		
		for(int i = 1;i <= constantNumber - 1;i++){
			
			int type = iter.nextU1ToInt();
			
			if(type == 1){
				//UTF8Info
				int length = iter.nextU2ToInt();
				byte[] bytes = iter.getBytes(length);
				String value = "";
				try {
					value = new String(bytes,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				UTF8Info utf8Info = new UTF8Info(constantPool);
				utf8Info.setLength(length);
				utf8Info.setValue(value);
				
				constantPool.addConstantInfo(utf8Info);
			}else if(type == 7){
				//ClassInfo
				int utf8Index = iter.nextU2ToInt();
				ClassInfo classInfo = new ClassInfo(constantPool);
				classInfo.setUtf8Index(utf8Index);
				
				constantPool.addConstantInfo(classInfo);
			}else if(type == 8){
				//StringInfo
				int index = iter.nextU2ToInt();
				StringInfo stringInfo = new StringInfo(constantPool);
				stringInfo.setIndex(index);
				
				constantPool.addConstantInfo(stringInfo);
			}else if(type == 9){
				//FieldRefInfo
				int classInfoIndex = iter.nextU2ToInt();
				int nameAndTypeIndex = iter.nextU2ToInt();
				FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
				fieldRefInfo.setClassInfoIndex(classInfoIndex);
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				
				constantPool.addConstantInfo(fieldRefInfo);
			}else if(type == 10){
				//MethodRefInfo
				int classInfoIndex = iter.nextU2ToInt();	
				int nameAndTypeIndex = iter.nextU2ToInt();
				MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
				methodRefInfo.setClassInfoIndex(classInfoIndex);
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				
				constantPool.addConstantInfo(methodRefInfo);
			}else if(type == 12){
				//NameAndTypeInfo
				int nameIndex = iter.nextU2ToInt();
				int descriptorIndex = iter.nextU2ToInt();
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
				nameAndTypeInfo.setIndex1(nameIndex);
				nameAndTypeInfo.setIndex2(descriptorIndex);
				
				constantPool.addConstantInfo(nameAndTypeInfo);
			}else{
				//
				throw new RuntimeException("该常量项还未实现！！！");
			}
		}
		
		return constantPool;
	}
    
}
