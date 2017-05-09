package com.coderising.jvm.loader;

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
import com.coderising.jvm.exception.AddAnotherParserException;
import com.coderising.jvm.exception.NotAClassFileException;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;
import com.coderising.jvm.util.Util;

public class ClassFileParser {
	
	public ClassFile parse(byte[] codes) throws NotAClassFileException  {

		ByteCodeIterator iterator = new ByteCodeIterator(codes);
		if(!validator(iterator)){
			throw new NotAClassFileException();
		}
		ClassFile clazzFile = new ClassFile();

		clazzFile.setMinorVersion(iterator.nextU2ToInt());

		clazzFile.setMajorVersion(iterator.nextU2ToInt());

		clazzFile.setConstPool(parseConstantPool(iterator,clazzFile));
		
		clazzFile.setAccessFlag(parseAccessFlag(iterator));
		
		clazzFile.setClassIndex(parseClassInfex(iterator));
		
		parseInterfaces(iterator);
			
		//解析属性
        int fieldCount = iterator.nextU2ToInt();		
		for (int i = 0; i < fieldCount; i++) {			
			clazzFile.addField(Field.parse(clazzFile.getConstantPool(), iterator));
		}
		//解析方法
		int methodCount = iterator.nextU2ToInt();
		for (int i = 0; i < methodCount; i++) {
			clazzFile.addMethod(Method.parse(clazzFile.getConstantPool(), iterator));;
		}
			
		return clazzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter){

		return new AccessFlag(iter.nextU2ToInt());
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter){

		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextU2ToInt());
		classIndex.setSuperClassIndex(iter.nextU2ToInt());
		return classIndex;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter,ClassFile clazzFile){
			
		ConstantPool pool = new ConstantPool(clazzFile);
		pool.addConstantInfo(new NullConstantInfo());
		
		try {
			
			int poolSize = iter.nextU2ToInt();
			for (int i = 1; i < poolSize; i++) {
			    int tag = iter.nextInt();		
				switch (tag) {
				case ConstantInfo.UTF8_INFO:
					UTF8Info UTF8Info = new UTF8Info(pool);
					int len = iter.nextU2ToInt();
					String value = iter.nextStr(len);
					UTF8Info.setLength(len);
					UTF8Info.setValue(value);
					pool.addConstantInfo(UTF8Info);				
					break;
			    case ConstantInfo.FLOAT_INFO:
			    	//TODO
					break;
			    case ConstantInfo.CLASS_INFO:
			    	ClassInfo classInfo = new ClassInfo(pool);
			    	classInfo.setUtf8Index(iter.nextU2ToInt());
			    	pool.addConstantInfo(classInfo);
					break;
			    case ConstantInfo.STRING_INFO:
			    	StringInfo stringInfo = new StringInfo(pool);
			    	stringInfo.setIndex(iter.nextU2ToInt());
			    	pool.addConstantInfo(stringInfo);
					break;
			    case ConstantInfo.FIELD_INFO:
			    	FieldRefInfo fieldRefInfo = new FieldRefInfo(pool);
			    	fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
			    	fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
			    	pool.addConstantInfo(fieldRefInfo);
					break;	
			    case ConstantInfo.METHOD_INFO:
			    	MethodRefInfo methodRefInfo = new MethodRefInfo(pool);
			    	methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
			    	methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
			    	pool.addConstantInfo(methodRefInfo);
					break;	
			    case ConstantInfo.NAME_AND_TYPE_INFO:
			    	NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(pool);
			    	nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
			    	nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
			    	pool.addConstantInfo(nameAndTypeInfo);
					break;		
				default:
					throw new AddAnotherParserException();
				}
			}
		} catch (AddAnotherParserException e) {
			e.printStackTrace();
		}	
		return pool;
	}
	
	private void parseInterfaces(ByteCodeIterator iter){
		
		@SuppressWarnings("unused")
		int interfaceCount = iter.nextU2ToInt();
		//System.out.println("接口数量为："+interfaceCount);
		// TODO
	}
	
	/**
	 * 校验是否为class文件
	 * @param iterator
	 * @return
	 */
	private boolean validator(ByteCodeIterator iterator){
		
		if(iterator.hasNext(4)){
			byte[] magicByte = iterator.next(4);
			String magicNumber = Util.byteToHexString(magicByte);
			if("cafebabe".equals(magicNumber)){
				return true;
			}
		}
		return false;
	}
}
