package jvm.loader;



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

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {

		
		ClassFile clzFile = new ClassFile();
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		iter.nextUxToHexString(4);
		
		int minorVersion = getMinorVersion(iter);
		clzFile.setMinorVersion(minorVersion);
		
		int majorVersion = getMajorVersion(iter);
		clzFile.setMajorVersion(majorVersion);
		
		ConstantPool constantPool = parseConstantPool(iter);
		clzFile.setConstPool(constantPool);
		
		AccessFlag accessFlag = parseAccessFlag(iter);
		clzFile.setAccessFlag(accessFlag);
		
		ClassIndex clzIndex = parseClassInfex(iter);
		clzFile.setClassIndex(clzIndex);
		
		return clzFile;
	}
	
	// 次版本号
	private int getMinorVersion(ByteCodeIterator iter){
		int minorVersion = iter.nextU2ToInt();
		return minorVersion;
	}
	
	// 主版本号
	private int getMajorVersion(ByteCodeIterator iter){
		int majorVersion = iter.nextU2ToInt();
		return majorVersion;
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
				int[] codes = new int[length];
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < length; j++){
					codes[j] = iter.nextU1toInt();
					sb.append((char)codes[j]);
				}
				String value = sb.toString();
				
				utf8Info.setLength(length);
				utf8Info.setValue(value);
				constantInfo = utf8Info;
				
			}else if(type == 7){
				ClassInfo classInfo = new ClassInfo(constantPool);
				int utf8Index = iter.nextU2ToInt();
				classInfo.setUtf8Index(utf8Index);
				constantInfo = classInfo;
				
			}else if(type == 8){
				StringInfo stringInfo = new StringInfo(constantPool);
				int index = iter.nextU2ToInt();
				stringInfo.setIndex(index);
				constantInfo = stringInfo;
				
			}else if(type == 9){
				FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
				int classInfoIndex = iter.nextU2ToInt();
				int nameAndTypeIndex = iter.nextU2ToInt();
				fieldRefInfo.setClassInfoIndex(classInfoIndex);
				fieldRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				constantInfo = fieldRefInfo;
				
			}else if(type == 10){
				MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
				int classInfoIndex = iter.nextU2ToInt();
				int nameAndTypeIndex = iter.nextU2ToInt();
				methodRefInfo.setClassInfoIndex(classInfoIndex);
				methodRefInfo.setNameAndTypeIndex(nameAndTypeIndex);
				constantInfo = methodRefInfo;
				
			}else if(type == 12){
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
				int nameIndex = iter.nextU2ToInt();
				int descIndex = iter.nextU2ToInt();
				nameAndTypeInfo.setIndex1(nameIndex);
				nameAndTypeInfo.setIndex2(descIndex);
				constantInfo = nameAndTypeInfo;
				
			}else{
				throw new RuntimeException("type: "+type+" 不在范围之内");
			}
			
			constantPool.addConstantInfo(constantInfo);
		}
		
		return constantPool;
	}
	

	
	
}
