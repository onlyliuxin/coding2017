package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.util.BytesIterUtil;
import com.coderising.jvm.util.ContantIterUtil;
import com.google.common.collect.Lists;

import java.util.List;

public class ClassFileParser {

	//解析字节码数组
	public ClassFile parse(byte[] codes) {
		ClassFile classFile = new ClassFile();
		//自定义字节码数组迭代器
		ByteCodeIterator iterator = new ByteCodeIterator(codes);
		classFile.setMagic(BytesIterUtil.byteToHexString(iterator.getNext(4)));
		classFile.setMinorVersion(BytesIterUtil.byteToInt(iterator.getNext(2)));
		classFile.setMajorVersion(BytesIterUtil.byteToInt(iterator.getNext(2)));
		ConstantPool pool = parseConstantPool(iterator);
		classFile.setConstantPool(pool);
		classFile.setAccessFlag(parseAccessFlag(iterator));
		classFile.setClzIndex(parseClassIndex(pool));
		return classFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		return new AccessFlag(BytesIterUtil.byteToInt(iter.getNext(2)));
	}

	private ClassIndex parseClassIndex(ConstantPool pool) {
		ClassIndex classIndex = new ClassIndex();
		List<ClassInfo> classInfos = Lists.newArrayList();
		for (int i = 0; i < pool.getSize(); i++) {
			ConstantInfo constantInfo = pool.getConstantInfo(i);
			if (constantInfo.getType() == ConstantInfo.CLASS_INFO) {
				classInfos.add((ClassInfo) constantInfo);
			}
		}
		classIndex.setThisClassIndex(classInfos.get(0).getUtf8Index());
		classIndex.setSuperClassIndex(classInfos.get(1).getUtf8Index());
		return classIndex;

	}

	//解析常量池
	private ConstantPool parseConstantPool(ByteCodeIterator iter) {

		int constantSize = BytesIterUtil.byteToInt(iter.getNext(2));
		int index = 0;
		ConstantPool constantPool = new ConstantPool();
		while (iter.hasNext() && index++ < constantSize) {
			constantPool.addConstantInfo(ContantIterUtil.parseType(constantPool, iter));
		}
		return constantPool;
	}

	
}
