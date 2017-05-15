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
 * 接收一个字节数组，把它变成若干对象，其中包括核心类 ClassFile
 */
public class ClassFileParser {
	
	// 返回核心类 ClassFile
	public ClassFile parse(byte[] codes){
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magicNumber = iter.nextU4ToHexString();
		if(!magicNumber.equals("cafebabe")){
			return null;
		}
		clzFile.setMinorVersion(iter.nextU2toInt());
		clzFile.setMajorVersion(iter.nextU2toInt());
		
		// 读取常量池
		ConstantPool pool = parseConstantPool(iter);
		AccessFlag accessFlag = parseAccessFlag(iter);
		ClassIndex clzIndex = parseClassIndex(iter);
		clzFile.setAccessFlag(accessFlag);
		clzFile.setClassIndex(clzIndex);
		clzFile.setConstantPool(pool);// 核心类中加入常量池
		
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
		
		// 给常量池里加常量类型信息
		for(int i = 1; i <= constantPoolCount-1; i++){// JVM 规定，常量数要 减去 1，减去 1后才是真正的常量个数
			
			int tag = iter.nextU1toInt();
			if(tag == 7){
				// 类或接口的符号引用   ClassInfo
				int utf8Index = iter.nextU2toInt();
				ClassInfo clzInfo= new ClassInfo(pool);// 生成一个 ClassInfo 的 对象
				clzInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(clzInfo);
			}else if(tag == 1){
				// UTF-8 编码的字符串
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
				// NameAndTypeInfo   字段或方法的部分符号引用
				int NameIndex = iter.nextU2toInt();
				int TypeIndex = iter.nextU2toInt();
				NameAndTypeInfo nameAndType = new NameAndTypeInfo(pool);
				nameAndType.setIndex1(NameIndex);
				nameAndType.setIndex2(TypeIndex);
				pool.addConstantInfo(nameAndType);
			}else if(tag == 9){
				// FieldRefInfo     字段的符号引用
				int classInfoIndex = iter.nextU2toInt();
				int nameAndTypeIndex = iter.nextU2toInt();
				FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
				fieldRefInfo.setClassInfoIndex(classInfoIndex);
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				pool.addConstantInfo(fieldRefInfo);
			}else if(tag == 10){
				// MethodRefInfo  类中方法的符号引用
				int classInfoIndex = iter.nextU2toInt();
				int nameAndTypeIndex = iter.nextU2toInt();
				MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
				methodRefInfo.setClassInfoIndex(classInfoIndex);
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				pool.addConstantInfo(methodRefInfo);
			}else if(tag == 8){
				int stringIndex = iter.nextU2toInt();// 指向字符串字面量的索引
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
