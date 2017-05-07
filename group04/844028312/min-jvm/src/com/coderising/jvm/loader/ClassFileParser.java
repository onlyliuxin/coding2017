package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.CommandParser;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;
import com.coderising.jvm.util.Util;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile clf=new ClassFile();
		ByteCodeIterator iter=new ByteCodeIterator(codes);
		
		String magicNumber=iter.nextU4toString();
		if(!"cafebabe".equals(magicNumber)){
			return null;
		}
		int minorVersion=iter.nextU2toInt();
		int majorVersion=iter.nextU2toInt();
		clf.setMajorVersion(majorVersion);
		clf.setMinorVersion(minorVersion);
		ConstantPool pool=parseConstantPool(iter);
		clf.setConstPool(pool);
		
		AccessFlag accessFlag=parseAccessFlag(iter);
		clf.setAccessFlag(accessFlag);
		
		ClassIndex clzIndex=parseClassInfex(iter);
		clf.setClassIndex(clzIndex);
		
		parseInterfaces(iter);
		parseField(clf,iter);
		parseMethod(clf,iter);
		for(Method m:clf.getMethods()){
			ByteCodeCommand [] cmds =CommandParser.parse(clf,m.getCodeAttr().getCode() );
			m.getCodeAttr().setCmds(cmds);
		}
		
		return clf;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		
		int flag=iter.nextU2toInt();
		AccessFlag accessFlag=new AccessFlag(flag);
		return accessFlag;
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex clzIndex=new ClassIndex();
		int thisClassIndex=iter.nextU2toInt();
		int superClassIndex=iter.nextU2toInt();
		clzIndex.setSuperClassIndex(superClassIndex);
		clzIndex.setThisClassIndex(thisClassIndex);
		return clzIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		ConstantPool constantPool=new ConstantPool();
		int poolNum=iter.nextU2toInt();
		constantPool.addConstantInfo(new NullConstantInfo());
		for(int i=1;i<poolNum;i++){
			int tag=iter.nextU1toInt();
			if(tag==ConstantInfo.CLASS_INFO){
				ClassInfo classInfo=new ClassInfo(constantPool);
				int nameIndex=iter.nextU2toInt();
				classInfo.setUtf8Index(nameIndex);
				constantPool.addConstantInfo(classInfo);
			}
			else if(tag==ConstantInfo.FIELD_INFO){
				FieldRefInfo field=new FieldRefInfo(constantPool);
				int classIndex=iter.nextU2toInt();
				int nameAndTypeIndex=iter.nextU2toInt();
				field.setClassInfoIndex(classIndex);
				field.setNameAndTypeIndex(nameAndTypeIndex);
				constantPool.addConstantInfo(field);
			}
			else if(tag==ConstantInfo.FLOAT_INFO){
				
			}
			else if(tag==ConstantInfo.METHOD_INFO){
				MethodRefInfo methodInfo=new MethodRefInfo(constantPool);
				int classIndex=iter.nextU2toInt();
				int nameAndTypeIndex=iter.nextU2toInt();
				methodInfo.setClassInfoIndex(classIndex);
				methodInfo.setNameAndTypeIndex(nameAndTypeIndex);
				constantPool.addConstantInfo(methodInfo);
			}
			else if(tag==ConstantInfo.NAME_AND_TYPE_INFO){
				NameAndTypeInfo nameAndType=new NameAndTypeInfo(constantPool);
				int nameIndex=iter.nextU2toInt();
				int nameAndTypeIndex=iter.nextU2toInt();
				nameAndType.setIndex1(nameIndex);
				nameAndType.setIndex2(nameAndTypeIndex);
				constantPool.addConstantInfo(nameAndType);
			}
			else if(tag==ConstantInfo.STRING_INFO){
				StringInfo stringInfo=new StringInfo(constantPool);
				int stringIndex=iter.nextU2toInt();
				stringInfo.setIndex(stringIndex);
				constantPool.addConstantInfo(stringInfo);
			}
			else if(tag==ConstantInfo.UTF8_INFO){
				UTF8Info utf=new UTF8Info(constantPool);
				int length=iter.nextU2toInt();
				byte[] date=iter.getByte(length);
				String value = null;
				try {
					value = new String(date,"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				utf.setLength(length);
				utf.setValue(value);
				constantPool.addConstantInfo(utf);
			}
			else{
				new Exception("no find tag");
			}
		}
		return constantPool;
	}
	
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2toInt();

		System.out.println("interfaceCount:" + interfaceCount);

		// TODO : 如果实现了interface, 这里需要解析
	}
	
	private void  parseField(ClassFile clf,ByteCodeIterator iter){
		int fieldNum=iter.nextU2toInt(); 
		for(int i=0;i<fieldNum;i++){
			clf.addField(Field.parse(clf.getConstantPool(), iter));
		}
	}
	private void parseMethod(ClassFile clf,ByteCodeIterator iter){
		int methodNum=iter.nextU2toInt();
		for(int i=0;i<methodNum;i++){
			clf.addMethod(Method.parse(clf, iter));
		}
	}

	
}
