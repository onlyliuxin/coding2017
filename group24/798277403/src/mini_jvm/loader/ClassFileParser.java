package mini_jvm.loader;

import mini_jvm.clz.AccessFlag;
import mini_jvm.clz.ClassFile;
import mini_jvm.clz.ClassIndex;
import mini_jvm.constant.*;

import java.io.UnsupportedEncodingException;


public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile classFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		//1 先解析魔数
		String magicNumber = iter.nextU4ToHexString();
		if(!magicNumber.equals("cafebabe")){
			return null;
		}

		//2 解析次版本号 主版本号
		classFile.setMinorVersion(iter.nextU2ToInt());
		classFile.setMajorVersion(iter.nextU2ToInt());

		//3 解析常量池
		ConstantPool constantPool = parseConstantPool(iter);
		classFile.setConstPool(constantPool);

		//4 解析访问标志
		AccessFlag accessFlag = parseAccessFlag(iter);
		classFile.setAccessFlag(accessFlag);

		//5 解析类索引，父类索引，接口索引
		ClassIndex classIndex = parseClassInfex(iter);
		classFile.setClassIndex(classIndex);

		return classFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		return new AccessFlag(iter.nextU2ToInt());
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextU2ToInt());
		classIndex.setSuperClassIndex(iter.nextU2ToInt());
		return classIndex;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		//常量池容量计数值，从1开始，不引用任何一个常量池项目就标为0
		int countConstant = iter.nextU2ToInt();

		ConstantPool constantPool = new ConstantPool();

		//先设置第0个常量项
		constantPool.addConstantInfo(new NullConstantInfo());

		for(int i=1; i<countConstant; i++){
			int tag = iter.nextU1toInt();
			switch (tag){
				case 1 :
					//utf-8编码的字符串
					UTF8Info utf8Str = new UTF8Info(constantPool);

					//解析字符串长度
					int length = iter.nextU2ToInt();
					utf8Str.setLength(length);
					//根据长度解析出字符串
					byte[] bytes = iter.getBytes(length);
					try {
						utf8Str.setValue(new String(bytes,"UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					constantPool.addConstantInfo(utf8Str);
					break;

				case 7:
					//指向全限定名常量项的索引
					ClassInfo classInfo = new ClassInfo(constantPool);

					int index = iter.nextU2ToInt();
					classInfo.setUtf8Index(index);

					constantPool.addConstantInfo(classInfo);
					break;

				case 8:
					//指向字符串字面量的索引
					StringInfo stringInfo = new StringInfo(constantPool);
					stringInfo.setIndex(iter.nextU2ToInt());
					constantPool.addConstantInfo(stringInfo);
					break;

				case 9:
					//指向声明字段的类或者接口的Class_info的索引项
					FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
					fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
					fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
					constantPool.addConstantInfo(fieldRefInfo);
					break;

				case 10:
					//指向声明方法的类描述符class_info的索引项
					MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
					methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
					methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
					constantPool.addConstantInfo(methodRefInfo);
					break;

				case 12:
					//指向该字段或方法名称的常量项的索引
					NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
					nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
					nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
					constantPool.addConstantInfo(nameAndTypeInfo);
					break;

				default:
					throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
			}
		}
		return constantPool;
	}

	
}
