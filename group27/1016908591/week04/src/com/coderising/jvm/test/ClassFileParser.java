package com.coderising.jvm.test;

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

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		String magicNumber = iter.nextU4ToHexString();
		if(!"cafebabe".equals(magicNumber)){
			return null;
		}
		//读版本号
		clzFile.setMinorVersion(iter.nextU2ToInt());
		clzFile.setMajorVersion(iter.nextU2ToInt());
		//读常量池
		ConstantPool pool = parseConstantPool(iter);
		clzFile.setConstPool(pool);
		return clzFile;
	} 

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

		return null;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {

		return null;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		//取出常量池的个数
		int constPoolCount = iter.nextU2ToInt();
		System.out.println("Constant pool Count:"+constPoolCount);
		//定义一个常量池
		ConstantPool pool = new ConstantPool();
		//因为数组的第零项是无效的，数组从0开始，但是JVM确从1开始
		pool.addConstantInfo(new NullConstantInfo());
		
		for(int i = 1;i<=constPoolCount-1;i++){
			int tag = iter.nextU1ToInt();
			
			if(tag == 7){
				//class Info
				int utf8Index = iter.nextU2ToInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);
				//把classInfo加入到常量池中
				pool.addConstantInfo(clzInfo);
			}else if(tag == 1){
				int len = iter.nextU2ToInt();
				byte[] data = iter.getBytes(len);
				String value = null;
				try {
					value = new String(data,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UTF8Info utf8str = new UTF8Info(pool);
				utf8str.setLength(len);
				utf8str.setValue(value);
				pool.addConstantInfo(utf8str);
				
			}
			else if(tag == 8){
				StringInfo info = new StringInfo(pool);
				info.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(info);
			}else if(tag == 9){
				FieldRefInfo field = new FieldRefInfo(pool);
				field.setClassInfoIndex(iter.nextU2ToInt());
				field.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(field);
			}else if (tag == 10) {
				MethodRefInfo method = new MethodRefInfo(pool);
				method.setClassInfoIndex(iter.nextU2ToInt());
				method.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(method);
				
			}else if(tag == 12){
				NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
				nameType.setIndex1(iter.nextU2ToInt());
				nameType.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(nameType);
			}else{
				throw new RuntimeErrorException(null, "the constant pool tag"+tag+"has not been implemented yet ");
			}
		}
		
		
		System.out.println("Finshed reading Constant pool");
		return pool;
	}

	
}
