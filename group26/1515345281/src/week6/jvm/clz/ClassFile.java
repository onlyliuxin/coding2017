package week6.jvm.clz;

import java.util.ArrayList;
import java.util.List;

import week6.jvm.constant.ConstantPool;
import week6.jvm.field.Field;
import week6.jvm.method.Method;

public class ClassFile {

	private int minorVersion;
	private int majorVersion;
	
	private ConstantPool constantPool;
	
	private AccessFlag accessFlag;
	
	private ClassIndex classIndex;
	
	private List<Field> fields=new ArrayList<Field>();
	
	private List<Method> methods=new ArrayList<Method>();
	
	public int getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}
	

	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public void setConstantPool(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}

	public AccessFlag getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	}

	public ClassIndex getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(ClassIndex classIndex) {
		this.classIndex = classIndex;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

}
