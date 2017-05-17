
package com.github.HarryHook.coding2017.jvm.method;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.cmd.ByteCodeCommand;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.github.HarryHook.coding2017.jvm.attr.AttributeInfo;
import com.github.HarryHook.coding2017.jvm.attr.CodeAttr;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.constant.UTF8Info;
import com.github.HarryHook.coding2017.jvm.loader.ByteCodeIterator;

public class Method {

    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private CodeAttr codeAttr;

    private ClassFile clzFile;

    public ClassFile getClzFile() {
	return clzFile;
    }

    public int getNameIndex() {
	return nameIndex;
    }

    public int getDescriptorIndex() {
	return descriptorIndex;
    }

    public CodeAttr getCodeAttr() {
	return codeAttr;
    }

    public void setCodeAttr(CodeAttr code) {
	this.codeAttr = code;
    }

    public Method(ClassFile clzFile, int accessFlag, int nameIndex, int descriptorIndex) {
	this.clzFile = clzFile;
	this.accessFlag = accessFlag;
	this.nameIndex = nameIndex;
	this.descriptorIndex = descriptorIndex;
    }

    public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {
	int accessFlag = iter.nextU2ToInt();
	int nameIndex = iter.nextU2ToInt();
	int descriptorIndex = iter.nextU2ToInt();
	int attributeCount = iter.nextU2ToInt();

	Method m = new Method(clzFile, accessFlag, nameIndex, descriptorIndex);

	for (int i = 1; i <= attributeCount; i++) {
	    int attrNameIndex = iter.nextU2ToInt();
	    String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
	    iter.back(2);

	    if (AttributeInfo.CODE.equalsIgnoreCase(attrName)) {
		CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
		m.setCodeAttr(codeAttr);
	    } else {
		throw new RuntimeException("only Attribute is complemented!");
	    }

	}
	return m;

    }

    public String toString() {
	ConstantPool pool = this.clzFile.getConstantPool();
	StringBuffer buffer = new StringBuffer();
	String name = ((UTF8Info) pool.getConstantInfo(this.nameIndex)).getValue();
	String desc = ((UTF8Info) pool.getConstantInfo(this.descriptorIndex)).getValue();
	buffer.append(name).append(":").append(desc).append("\n");
	buffer.append(this.codeAttr.toString(pool));
	return buffer.toString();
    }

    public ByteCodeCommand[] getCmds() {
	return this.getCodeAttr().getCmds();
    }

    private String getParamAndReturnType() {
	UTF8Info nameAndTypeInfo = (UTF8Info) this.getClzFile().getConstantPool()
		.getConstantInfo(this.getDescriptorIndex());
	return nameAndTypeInfo.getValue();
    }

    public List<String> getParameterList() {

	// e.g. (Ljava/util/List;Ljava/lang/String;II)V
	String paramAndType = getParamAndReturnType();

	int first = paramAndType.indexOf("(");
	int last = paramAndType.lastIndexOf(")");
	// e.g. Ljava/util/List;Ljava/lang/String;II
	String param = paramAndType.substring(first + 1, last);

	List<String> paramList = new ArrayList<String>();

	if ((null == param) || "".equals(param)) {
	    return paramList;
	}

	while (!param.equals("")) {

	    int pos = 0;
	    // 这是一个对象类型
	    if (param.charAt(pos) == 'L') {

		int end = param.indexOf(";");

		if (end == -1) {
		    throw new RuntimeException("can't find the ; for a object type");
		}
		paramList.add(param.substring(pos + 1, end));

		pos = end + 1;

	    } else if (param.charAt(pos) == 'I') {
		// int
		paramList.add("I");
		pos++;

	    } else if (param.charAt(pos) == 'F') {
		// float
		paramList.add("F");
		pos++;

	    } else {
		throw new RuntimeException("the param has unsupported type:" + param);
	    }

	    param = param.substring(pos);

	}
	return paramList;

    }
}