package week5.jvm.loader;

import java.io.UnsupportedEncodingException;

import week5.jvm.clz.AccessFlag;
import week5.jvm.clz.ClassFile;
import week5.jvm.clz.ClassIndex;
import week5.jvm.constant.ClassInfo;
import week5.jvm.constant.ConstantPool;
import week5.jvm.constant.FieldRefInfo;
import week5.jvm.constant.MethodRefInfo;
import week5.jvm.constant.NameAndTypeInfo;
import week5.jvm.constant.NullConstantInfo;
import week5.jvm.constant.StringInfo;
import week5.jvm.constant.UTF8Info;

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
		
		return clzFile;
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
	        switch(tag){
	        case 1:
	        	int len=iter.nextU2ToInt();
	        	byte[] data=iter.getBytes(len);
	        	
	        	String value=null;
	        	try {
					value=new String(data,"UTF-8");
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
	        	
	        	UTF8Info utf8Str=new UTF8Info(pool);
	        	utf8Str.setLength(len);
	        	utf8Str.setValue(value);
	        	
	        	pool.addConstantInfo(utf8Str);
	        	break;
	        case 7:
	        	ClassInfo clzInfo=new ClassInfo(pool);
	        	clzInfo.setUtf8Index(iter.nextU2ToInt());
	        	
	        	pool.addConstantInfo(clzInfo);
	        	break;
	        case 8:
	        	
	        	StringInfo stringInfo=new StringInfo(pool);
	        	stringInfo.setIndex(iter.nextU2ToInt());
	        	
	        	pool.addConstantInfo(stringInfo);
	        	break;
	        case 9:
	            FieldRefInfo field=new FieldRefInfo(pool);
	            field.setClassInfoIndex(iter.nextU2ToInt());
	            field.setNameAndTypeIndex(iter.nextU2ToInt());
	            
	            pool.addConstantInfo(field);
	        	break;
	        case 10:
	        	MethodRefInfo method=new MethodRefInfo(pool);
	        	method.setClassInfoIndex(iter.nextU2ToInt());
	        	method.setNameAndTypeIndex(iter.nextU2ToInt());
	        	
	        	pool.addConstantInfo(method);
	        	break;
	        case 12:
	        	NameAndTypeInfo nameType=new NameAndTypeInfo(pool);
	        	nameType.setNameIndex(iter.nextU2ToInt());
	        	nameType.setTypeIndex(iter.nextU2ToInt());
	        	
	        	pool.addConstantInfo(nameType);
	        	break;
	        default:
	        	throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
	        }
		}
		System.out.println("Finished reading Constant pool ");
		
		return pool;
	}
}
