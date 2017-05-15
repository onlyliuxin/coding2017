package jvm.loader;



import java.io.UnsupportedEncodingException;

import jvm.clz.AccessFlag;
import jvm.clz.ClassFile;
import jvm.clz.ClassIndex;
import jvm.constant.ClassInfo;
import jvm.constant.ConstantInfo;
import jvm.constant.ConstantPool;
import jvm.constant.FieldRefInfo;
import jvm.constant.MethodRefInfo;
import jvm.constant.NameAndTypeInfo;
import jvm.constant.NullConstantInfo;
import jvm.constant.StringInfo;
import jvm.constant.UTF8Info;
import jvm.field.Field;
import jvm.method.Method;

public class ClassFileParser {

	private ConstantPool constantPool;
	
	public ClassFile parse(byte[] codes) {

		
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		iter.nextUxToHexString(4);
		
		clzFile.setMinorVersion(iter.nextU2ToInt());
		clzFile.setMajorVersion(iter.nextU2ToInt());
		
		this.constantPool = parseConstantPool(iter);
		clzFile.setConstPool(constantPool);
		
		AccessFlag accessFlag = parseAccessFlag(iter);
		clzFile.setAccessFlag(accessFlag);
		
		ClassIndex clzIndex = parseClassInfex(iter);
		clzFile.setClassIndex(clzIndex);
		
		parseInterfaces(iter);//接口
		
		parseFileds(clzFile, iter);
		
		parseMethods(clzFile, iter);
		
		
		
		return clzFile;
	}
	
	

	// 访问标志
	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		int flagValue = iter.nextU2ToInt();
		AccessFlag accessFlag = new AccessFlag(flagValue);
		return accessFlag;
	}

	// 本类和父类
	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		int thisClassIndex = iter.nextU2ToInt();
		int superClassIndex = iter.nextU2ToInt();
		
		classIndex.setThisClassIndex(thisClassIndex);
		classIndex.setSuperClassIndex(superClassIndex);
		
		return classIndex;

	}

	// 常量池
	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		ConstantPool constantPool = new ConstantPool();
		int poolSize = iter.nextU2ToInt();
		constantPool.addConstantInfo(new NullConstantInfo());
		for(int i = 1; i < poolSize; i++){
			ConstantInfo constantInfo = null;
			
			int type = iter.nextU1toInt();
			if(type == 1){
				UTF8Info utf8Info = new UTF8Info(constantPool);
				int length = iter.nextU2ToInt();
				byte[] data = iter.getBytes(length);
				String value = null;
				try {
					value = new String(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				utf8Info.setLength(length);
				utf8Info.setValue(value);
				constantInfo = utf8Info;
				
			}else if(type == 7){
				ClassInfo classInfo = new ClassInfo(constantPool);
				classInfo.setUtf8Index(iter.nextU2ToInt());
				constantInfo = classInfo;
				
			}else if(type == 8){
				StringInfo stringInfo = new StringInfo(constantPool);
				stringInfo.setIndex(iter.nextU2ToInt());
				constantInfo = stringInfo;
				
			}else if(type == 9){
				FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
				fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				constantInfo = fieldRefInfo;
				
			}else if(type == 10){
				MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
				methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				constantInfo = methodRefInfo;
				
			}else if(type == 12){
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
				nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
				nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
				constantInfo = nameAndTypeInfo;
				
			}else{
				throw new RuntimeException("type: "+type+" 不在范围之内");
			}
			
			constantPool.addConstantInfo(constantInfo);
		}
		
		return constantPool;
	}
	
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();

//		System.out.println("interfaceCount:" + interfaceCount);

		// TODO : 如果实现了interface, 这里需要解析
	}

	private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
		int fieldCount = iter.nextU2ToInt();
		for(int i = 0; i < fieldCount; i++){
			Field field = Field.parse(clzFile.getConstantPool(), iter);
			clzFile.addField(field);
		}

	}

	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {
		int methodCount = iter.nextU2ToInt();
		for(int i = 0; i < methodCount; i++){
			Method method = Method.parse(clzFile, iter);
			clzFile.addMethod(method);
		}
		

	}
	
	
}
