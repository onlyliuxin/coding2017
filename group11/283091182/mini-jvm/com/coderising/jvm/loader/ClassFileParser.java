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

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		ClassFile clzFile = new ClassFile();
		
		String magicNum = iter.getBytesAsHexString(4);
		if(!"cafebabe".equals(magicNum)){
			throw new RuntimeException("Magic Number validation failure, this may not be a java class file.");
		}
		
		int minVer = iter.nextU2AsInt();
		int majVer = iter.nextU2AsInt();
		System.out.println("magicNum="+magicNum+",minVer="+minVer+",majVer="+majVer);
		clzFile.setMajorVersion(majVer);
		clzFile.setMinorVersion(minVer);
		ConstantPool  pool = parseConstantPool(iter);
		clzFile.setConstPool(pool);
		AccessFlag accFlag = parseAccessFlag(iter);
		clzFile.setAccessFlag(accFlag);
		ClassIndex clsIdx =  parseClassIndex(iter);
		clzFile.setClassIndex(clsIdx);
		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag af = new AccessFlag(iter.nextU2AsInt());
		return af;
	}

	private ClassIndex parseClassIndex(ByteCodeIterator iter) {
		ClassIndex ci = new ClassIndex();
		ci.setThisClassIndex(iter.nextU2AsInt());
		ci.setSuperClassIndex(iter.nextU2AsInt());
		return ci;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		ConstantPool cp = new ConstantPool();
		cp.addConstantInfo(new NullConstantInfo());
		int cpLen = iter.nextU2AsInt();
		for(int i=0;i<cpLen-1;i++){
			int typeFlag = iter.nextU1AsInt();
			switch(typeFlag){
			case ConstantInfo.CLASS_INFO: //class info
				ClassInfo cf = new ClassInfo(cp);
				cf.setUtf8Index(iter.nextU2AsInt());
				cp.addConstantInfo(cf);
				break;
			case ConstantInfo.UTF8_INFO:	//UTF8 info
				UTF8Info ui = new UTF8Info(cp);
				ui.setLength(iter.nextU2AsInt());
				ui.setValue(iter.getBytesAsString(ui.getLength()));
				cp.addConstantInfo(ui);
				break;
			case ConstantInfo.STRING_INFO:
				StringInfo ci = new StringInfo(cp);
				ci.setIndex(iter.nextU2AsInt());
				cp.addConstantInfo(ci);
				break;
			case ConstantInfo.FIELD_INFO:
				FieldRefInfo fri = new FieldRefInfo(cp);
				fri.setClassInfoIndex(iter.nextU2AsInt());
				fri.setNameAndTypeIndex(iter.nextU2AsInt());
				cp.addConstantInfo(fri);
				break;
			case ConstantInfo.METHOD_INFO:
				MethodRefInfo mri = new MethodRefInfo(cp);
				mri.setClassInfoIndex(iter.nextU2AsInt());
				mri.setNameAndTypeIndex(iter.nextU2AsInt());
				cp.addConstantInfo(mri);
				break;
			case ConstantInfo.NAME_AND_TYPE_INFO:
				NameAndTypeInfo nti = new NameAndTypeInfo(cp);
				nti.setIndex1(iter.nextU2AsInt());
				nti.setIndex2(iter.nextU2AsInt());
				cp.addConstantInfo(nti);
				break;
			default : throw new RuntimeException("Parse exception:"+typeFlag);
			}
		}
		return cp;
	}

}
