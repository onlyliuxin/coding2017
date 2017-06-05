package week6.jvm.loader;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import week6.jvm.clz.AccessFlag;
import week6.jvm.clz.ClassFile;
import week6.jvm.clz.ClassIndex;
import week6.jvm.constant.ClassInfo;
import week6.jvm.constant.ConstantInfo;
import week6.jvm.constant.ConstantPool;
import week6.jvm.constant.FieldRefInfo;
import week6.jvm.constant.MethodRefInfo;
import week6.jvm.constant.NameAndTypeInfo;
import week6.jvm.constant.NullConstantInfo;
import week6.jvm.constant.StringInfo;
import week6.jvm.constant.UTF8Info;
import week6.jvm.field.Field;
import week6.jvm.method.Method;

public class ClassFileParser {

	public ClassFileParser(){};
	
	public ClassFile parse(byte[] codes){ 
		
		ClassFile clzFile=new ClassFile();
		ByteCodeIterator iter=new ByteCodeIterator(codes);
		
		String magicNumber=iter.nextU4ToString();//获取魔数
		if(!"cafebabe".equals(magicNumber)){
			return null;
		}
		
		int minorVersion=iter.nextU2ToInt();//获取次版本
		int majorVersion=iter.nextU2ToInt();//获取主版本
		
		clzFile.setMinorVersion(minorVersion);
		clzFile.setMajorVersion(majorVersion);
		
		ConstantPool pool=parseConstantPool(iter);//常量池
		clzFile.setConstantPool(pool);
		
		AccessFlag accessFlag=parseAccessFlag(iter);//访问标志
		clzFile.setAccessFlag(accessFlag);
		
		
		ClassIndex classIndex=parseClassIndex(iter);//类父类接口索引
		clzFile.setClassIndex(classIndex);
		
		List<Field> fields=parseFields(clzFile,iter);//字段表
		clzFile.setFields(fields);
		
		List<Method> methods=parseMethods(clzFile,iter);//方法表
		clzFile.setMethods(methods);
		
		return clzFile;
	}

	private List<Method> parseMethods(ClassFile clzFile, ByteCodeIterator iter) {
		int methodCount=iter.nextU2ToInt();
		List<Method> methods=new ArrayList<Method>();
		
		for(int i=1;i <= methodCount;i++){
			Method method=Method.parseMethod(clzFile,iter);
			methods.add(method);
		}
		
		return methods;
	}

	private List<Field> parseFields(ClassFile clzFile,
			ByteCodeIterator iter) {
		
		int fieldCount=iter.nextU2ToInt();		
		List<Field> fields=new ArrayList<Field>();
		
		for(int i = 1;i <= fieldCount;i++){
			Field field=Field.parseField(clzFile,iter);
		    fields.add(field);
		}	
		System.out.println("Finished parse Fields ");
		return fields;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		ClassIndex classIndex=new ClassIndex();
		
		classIndex.setThisClassIndex(iter.nextU2ToInt());
		classIndex.setSuperClassIndex(iter.nextU2ToInt());
		classIndex.setInterfaceIndex(iter.nextU2ToInt());
		
		return classIndex;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		
		AccessFlag flag=new AccessFlag(iter.nextU2ToInt());
		
		return flag;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		
		int constantPoolCount=iter.nextU2ToInt();
		//System.out.println("Constant Pool Count :" + constantPoolCount);
		
		ConstantPool pool=new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		
		for(int i=1;i<=constantPoolCount-1;i++){		
			int tag=iter.nextU1ToInt();
			ConstantInfo info=ConstantInfo.parseConstantInfo(pool,tag,iter);
	        pool.addConstantInfo(info);
		}
		System.out.println("Finished reading Constant pool ");
		
		return pool;
	}
}
