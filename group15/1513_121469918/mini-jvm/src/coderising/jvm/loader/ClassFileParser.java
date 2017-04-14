package coderising.jvm.loader;

import java.io.UnsupportedEncodingException;

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
import coderising.jvm.field.Field;
import coderising.jvm.method.Method;

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
		
		parseInterfaces(iter);

		parseFileds(clzFile, iter);

		parseMethods(clzFile, iter);
		
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
			
			switch (tag){
			case 1:
				int len = iter.nextU2ToInt();
				byte[] data = iter.getBytes(len);
				String value = null;
				try {
					value = new String(data, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				UTF8Info utf8Str = new UTF8Info(pool);
				utf8Str.setLength(len);
				utf8Str.setValue(value);
				pool.addConstantInfo(utf8Str);
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
			default:
				throw new RuntimeException("noFoundConstantIndex:"+tag);
			}
		}
		System.out.println("Finished reading Constant pool ");
		return pool;
	}
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();

		System.out.println("interfaceCount:" + interfaceCount);

		// TODO : 如果实现了interface, 这里需要解析
	}

	private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
		int fieldCount = iter.nextU2ToInt();
		
		for (int i = 1; i <= fieldCount; i++) {
			Field f = Field.parse(clzFile.getConstantPool(), iter);
			clzFile.addField(f);			
		}

	}

	private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {

		int methodCount = iter.nextU2ToInt();

		for (int i = 1; i <= methodCount; i++) {
			Method m = Method.parse(clzFile, iter);
			clzFile.addMethod(m);
		}

	}
	
}
