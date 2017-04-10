package coderising.jvm.loader;

import coderising.jvm.clz.AccessFlag;
import coderising.jvm.clz.ClassFile;
import coderising.jvm.clz.ClassIndex;
import coderising.jvm.constant.ClassInfo;
import coderising.jvm.constant.ConstantPool;
import coderising.jvm.constant.FieldRefInfo;
import coderising.jvm.constant.MethodRefInfo;
import coderising.jvm.constant.NameAndTypeInfo;
import coderising.jvm.constant.NullConstantInfo;
import coderising.jvm.constant.StringInfo;
import coderising.jvm.constant.UTF8Info;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		
		String magicNumber = iter.nextU4ToHexString();
		if(!magicNumber.equals("cafebabe")){
			return null;
		}
		
		clzFile.setMinorVersion(iter.nextU2ToInt());
		clzFile.setMajorVersion(iter.nextU2ToInt());
		
		ConstantPool pool = parseConstantPool(iter);
		clzFile.setConstPool(pool);
		
		AccessFlag flag = parseAccessFlag(iter);
		clzFile.setAccessFlag(flag);
		
		ClassIndex clzindex = parseClassInfex(iter);
		clzFile.setClassIndex(clzindex);
		
		return clzFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		return new AccessFlag(iter.nextU2ToInt());
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex clzIndex = new ClassIndex();
		clzIndex.setThisClassIndex(iter.nextU2ToInt());
		clzIndex.setSuperClassIndex(iter.nextU2ToInt());
		return clzIndex;
	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		ConstantPool pool = new ConstantPool();
		pool.addConstantInfo(new NullConstantInfo());
		int poolSize = iter.nextU2ToInt();
		
		for(int i =1;i<poolSize;i++){
			int tag = iter.nextU1ToInt();
			System.out.println("tag:"+i+":"+tag);
			switch (tag){
			case 1:
				UTF8Info utf8Info = new UTF8Info(pool);
				utf8Info.setLength(iter.nextU2ToInt());
				String value = iter.nextString(utf8Info.getLength());
				utf8Info.setValue(value);
				pool.addConstantInfo(utf8Info);
				break;
			case 4:
				break;
			case 7:
				int utf8Index = iter.nextU2ToInt();
				ClassInfo clzInfo = new ClassInfo(pool);
				clzInfo.setUtf8Index(utf8Index);
				pool.addConstantInfo(clzInfo);
				break;
			case 8:
				StringInfo stringInfo = new StringInfo(pool);
				stringInfo.setIndex(iter.nextU2ToInt());
				pool.addConstantInfo(stringInfo);
				break;
			case 9:
				FieldRefInfo fieldRef = new FieldRefInfo(pool);
				fieldRef.setClassInfoIndex(iter.nextU2ToInt());
				fieldRef.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(fieldRef);
				break;
			case 10:
				MethodRefInfo methodRef = new MethodRefInfo(pool);
				methodRef.setClassInfoIndex(iter.nextU2ToInt());
				methodRef.setNameAndTypeIndex(iter.nextU2ToInt());
				pool.addConstantInfo(methodRef);
				break;
			case 12:
				NameAndTypeInfo nati = new NameAndTypeInfo(pool);
				nati.setIndex1(iter.nextU2ToInt());
				nati.setIndex2(iter.nextU2ToInt());
				pool.addConstantInfo(nati);
				break;
			}
		}
		return pool;
	}

	
}
