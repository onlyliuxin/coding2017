package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import javax.management.RuntimeErrorException;

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

/*
 * ����һ���ֽ����飬����������ɶ������а��������� ClassFile
 */
public class ClassFileParser {
	
	// ���غ����� ClassFile
	public ClassFile parse(byte[] codes){
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magicNumber = iter.nextU4ToHexString();
		if(!magicNumber.equals("cafebabe")){
			return null;
		}
		clzFile.setMinorVersion(iter.nextU2toInt());
		clzFile.setMajorVersion(iter.nextU2toInt());
		
		// ��ȡ������
		ConstantPool pool = parseConstantPool(iter);
		AccessFlag accessFlag = parseAccessFlag(iter);
		ClassIndex clzIndex = parseClassIndex(iter);
		clzFile.setAccessFlag(accessFlag);
		clzFile.setClassIndex(clzIndex);
		clzFile.setConstantPool(pool);// �������м��볣����
		
		parseInterfaces(iter);
		parseFields(clzFile,iter);
		parseMethods(clzFile,iter);
		
		
		return clzFile;
	}




	private AccessFlag parseAccessFlag(ByteCodeIterator iter){
		int accessFlagValue = iter.nextU2toInt();
		AccessFlag accessFlag = new AccessFlag(accessFlagValue);
		accessFlag.setFlagValue(accessFlagValue);
		return accessFlag;
	}
	
	private ClassIndex parseClassIndex(ByteCodeIterator iter){
		int thisClassIndex = iter.nextU2toInt();
		int superClassIndex = iter.nextU2toInt();
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(superClassIndex);
		return classIndex;
	}
	
	private ConstantPool parseConstantPool(ByteCodeIterator iter){
		
		int constantPoolCount = iter.nextU2toInt();
		System.out.println("ConstantPool Count : " + constantPoolCount);
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		
		// ����������ӳ���������Ϣ
		for(int i = 1; i <= constantPoolCount-1; i++){// JVM �涨��������Ҫ ��ȥ 1����ȥ 1����������ĳ�������
			
			int tag = iter.nextU1toInt();
			if(tag == 7){
				// ���ӿڵķ�������   ClassInfo
				int utf8Index = iter.nextU2toInt();
				ClassInfo clzInfo= new ClassInfo(pool);// ����һ�� ClassInfo �� ����
				clzInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(clzInfo);
			}else if(tag == 1){
				// UTF-8 ������ַ���
				int length = iter.nextU2toInt();
				byte data[] = iter.getByte(length);
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
				// NameAndTypeInfo   �ֶλ򷽷��Ĳ��ַ�������
				int NameIndex = iter.nextU2toInt();
				int TypeIndex = iter.nextU2toInt();
				NameAndTypeInfo nameAndType = new NameAndTypeInfo(pool);
				nameAndType.setIndex1(NameIndex);
				nameAndType.setIndex2(TypeIndex);
				pool.addConstantInfo(nameAndType);
			}else if(tag == 9){
				// FieldRefInfo     �ֶεķ�������
				int classInfoIndex = iter.nextU2toInt();
				int nameAndTypeIndex = iter.nextU2toInt();
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				fieldRefInfo.setClassInfoIndex(classInfoIndex);
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				pool.addConstantInfo(fieldRefInfo);
			}else if(tag == 10){
				// MethodRefInfo  ���з����ķ�������
				int classInfoIndex = iter.nextU2toInt();
				int nameAndTypeIndex = iter.nextU2toInt();
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				methodRefInfo.setClassInfoIndex(classInfoIndex);
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				pool.addConstantInfo(methodRefInfo);
			}else if(tag == 8){
				int stringIndex = iter.nextU2toInt();// ָ���ַ���������������
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
		int interfacesCount = iter.nextU2toInt();
		System.out.println("interfacesCount: " + interfacesCount);
		if(interfacesCount > 0){
			throw new RuntimeException("interfaces has not been implemented");
		}
		
	}
	
	private void parseFields(ClassFile clzFile, ByteCodeIterator iter) {
		int fieldsCount = iter.nextU2toInt();
		System.out.println("fieldsCount: " + fieldsCount);
		for(int i = 0; i < fieldsCount; i++){
			Field f = Field.parse(clzFile.getConstantPool(), iter);
			clzFile.addField(f);
		}
		
	}
	
	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {
		int methodsCount = iter.nextU2toInt();
		System.out.println("methodsCount: " + methodsCount);
		for(int i = 0; i < methodsCount; i++){
			Method m = Method.parse(clzFile, iter);
			clzFile.addMethod(m);
		}
		
	}

}
