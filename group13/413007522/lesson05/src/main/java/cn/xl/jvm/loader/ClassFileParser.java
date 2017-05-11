package cn.xl.jvm.loader;

import java.io.UnsupportedEncodingException;

import cn.xl.jvm.clz.AccessFlag;
import cn.xl.jvm.clz.ClassFile;
import cn.xl.jvm.clz.ClassIndex;
import cn.xl.jvm.constant.ClassInfo;
import cn.xl.jvm.constant.ConstantPool;
import cn.xl.jvm.constant.FieldRefInfo;
import cn.xl.jvm.constant.MethodRefInfo;
import cn.xl.jvm.constant.NameAndTypeInfo;
import cn.xl.jvm.constant.NullConstantInfo;
import cn.xl.jvm.constant.StringInfo;
import cn.xl.jvm.constant.UTF8Info;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		ClassFile classFile = new ClassFile();
		
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		
		if(!"cafebabe".equals(iter.nextU4ToHexString())){
			return null;
		}
		classFile.setMinorVersion(iter.nextU2ToInt());
		classFile.setMajorVersion(iter.nextU2ToInt());
		classFile.setAccessFlag(this.parseAccessFlag(iter));
		classFile.setClassIndex(this.parseClassIndex(iter));
		classFile.setConstPool(this.parseConstantPool(iter));
		
		
		return classFile;
	}



	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

		/*AccessFlag accessFlag = new AccessFlag(iter.nextU2ToInt());
		
		return accessFlag;*/
		return null;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {

		return null;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {

		int constantCount = iter.nextU2ToInt();
		System.out.println("constantPool count："+constantCount);

		ConstantPool constantPool = new ConstantPool();
		constantPool.addConstantInfo(new NullConstantInfo());

		for(int i = 0; i < constantCount; i++){
			int tag = iter.nextU1ToInt();
			if(tag == 7){
				
				int utf8Index = iter.nextU2ToInt();
				ClassInfo classInfo = new ClassInfo(constantPool);
				classInfo.setUtf8Index(utf8Index);
				constantPool.addConstantInfo(classInfo);
			}else if(tag == 1){
				
				int length = iter.nextU2ToInt();
				UTF8Info utf8Info = new UTF8Info(constantPool);
				utf8Info.setLength(length);
				byte[] b = iter.nextLenBytes(length);
				String strValue = null;
				try {
					strValue = new String(b,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				utf8Info.setValue(strValue);
				constantPool.addConstantInfo(utf8Info);
			}else if(tag == 8){
				
				int index = iter.nextU2ToInt();
				StringInfo stringInfo = new StringInfo(constantPool);
				stringInfo.setIndex(index);
				constantPool.addConstantInfo(stringInfo);
			}else if(tag == 9){
				
				int classInfoindex = iter.nextU2ToInt();
				int nameTypeIndex = iter.nextU2ToInt();
				FieldRefInfo filedRefInfo = new FieldRefInfo(constantPool);
				filedRefInfo.setClassInfoIndex(classInfoindex);
				filedRefInfo.setNameAndTypeIndex(nameTypeIndex);
				constantPool.addConstantInfo(filedRefInfo);
			}else if(tag == 10){
				
				int classInfoindex = iter.nextU2ToInt();
				int nameTypeIndex = iter.nextU2ToInt();
				MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
				methodRefInfo.setClassInfoIndex(classInfoindex);
				methodRefInfo.setNameAndTypeIndex(nameTypeIndex);
				constantPool.addConstantInfo(methodRefInfo);
			}else if(tag == 12){
				
				int nameIndex = iter.nextU2ToInt();
				int descIndex = iter.nextU2ToInt();
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
				nameAndTypeInfo.setIndex1(nameIndex);
				nameAndTypeInfo.setIndex2(descIndex);
				constantPool.addConstantInfo(nameAndTypeInfo);
			}else{
				//throw new RuntimeException("the constant Pool tag "+tag+" has not been implement yet！");
			}

		}

		return constantPool;
	}



}
