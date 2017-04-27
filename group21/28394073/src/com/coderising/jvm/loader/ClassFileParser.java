package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile clzfile = new ClassFile();
		//Yang:首先判断二进制数组是否是java的class文件
		ByteCodeIterator iterator = new ByteCodeIterator(codes);
		String magicNum = iterator.nextU4ToHexString();
		System.out.println(magicNum);
		if(!magicNum.equals("cafebabe")){
			System.out.println("不是正确的java类文件");
			return null;
		}else{
			clzfile.setMinorVersion(iterator.nextU2ToInt());
			clzfile.setMajorVersion(iterator.nextU2ToInt());
			
			clzfile.setConstPool(this.parseConstantPool(iterator));
			
			clzfile.setAccessFlag(this.parseAccessFlag(iterator));
			
			clzfile.setClassIndex(this.parseClassInfex(iterator));
			
		}
		return clzfile;
		

	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag acsFlag = new AccessFlag(iter.nextU2ToInt());
		return acsFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex clzIndex = new ClassIndex();
		clzIndex.setThisClassIndex(iter.nextU2ToInt());
		clzIndex.setSuperClassIndex(iter.nextU2ToInt());
		return clzIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {

		int constantPoolCount = iter.nextU2ToInt();
		System.out.println("常量池的个数：" + constantPoolCount);
		
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());//利用了空模式
		for(int i=1;i<constantPoolCount;i++){
//			ConstantInfo info = new ConstantInfo();//ConstantInfo是Abstract抽象类，不能被实例化
			int tag = iter.nextU1ToInt();
			if(tag == 1){ //UTF8
				int length = iter.nextU2ToInt();
				UTF8Info utf8 = new UTF8Info(pool);
				utf8.setLength(length);
				utf8.setValue(iter.getBytesToString(length));
				pool.addConstantInfo(utf8);
			}
			else if(tag == 7){//Class
				int utf8Index = iter.nextU2ToInt(); //因为classInfo中的U2必须是utf8_info结构，所以这里的index必须指向一个UTF8
				ClassInfo clz = new ClassInfo(pool);
				clz.setUtf8Index(utf8Index);
				pool.addConstantInfo(clz);
			}
			else if(tag == 8){//String
				StringInfo str = new StringInfo(pool);
				int utf8Index = iter.nextU2ToInt();
				str.setIndex(utf8Index);
				pool.addConstantInfo(str);
			}
			else if(tag == 9){//Fieldref
				FieldRefInfo field = new FieldRefInfo(pool);
				int classIndex = iter.nextU2ToInt();
				field.setClassInfoIndex(classIndex);
				int name_and_type_index = iter.nextU2ToInt();
				field.setNameAndTypeIndex(name_and_type_index);
				pool.addConstantInfo(field);
			}
			else if(tag == 10){//Methodref
				MethodRefInfo method = new MethodRefInfo(pool);
				int classIndex = iter.nextU2ToInt();
				method.setClassInfoIndex(classIndex);
				int name_and_type_index = iter.nextU2ToInt();
				method.setNameAndTypeIndex(name_and_type_index);
				pool.addConstantInfo(method);
			}
			else if(tag == 12){//NameAndType
				NameAndTypeInfo nameAndType = new NameAndTypeInfo(pool);
				int utf8Index = iter.nextU2ToInt();
				int utf8DescIndex = iter.nextU2ToInt();
				nameAndType.setIndex1(utf8Index);
				nameAndType.setIndex2(utf8DescIndex);
				pool.addConstantInfo(nameAndType);
			}
			else{
				throw new RuntimeException("The constant pool tag :" + tag + "still not be implemented." );
			}
		}
		return pool;
	}

	
}
