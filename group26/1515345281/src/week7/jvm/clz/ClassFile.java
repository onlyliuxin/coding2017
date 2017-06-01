package week7.jvm.clz;

import java.util.ArrayList;
import java.util.List;

import week7.jvm.constant.ClassInfo;
import week7.jvm.constant.ConstantPool;
import week7.jvm.field.Field;
import week7.jvm.method.Method;

public class ClassFile {

	private int minorVersion;
	private int majorVersion;
	
	private ConstantPool constantPool;
	
	private AccessFlag accessFlag;
	
	private ClassIndex classIndex;
	
	private List<Field> fields=new ArrayList<Field>();
	
	private List<Method> methods=new ArrayList<Method>();
	
	public String getClassName(){
	  int thisClassIndex=this.classIndex.getThisClassIndex();
	  ClassInfo info=(ClassInfo) this.constantPool.getConstantInfo(thisClassIndex);
	  return info.getClassName();
	}
	
	public String getSuperClassName(){
		int superClassIndex=this.classIndex.getSuperClassIndex();
		ClassInfo info=(ClassInfo) this.constantPool.getConstantInfo(superClassIndex);
		return info.getClassName();
	}
	
	public Method getMethod(String methodName,String paramAndReturnType){
		
		for(Method method:methods){
			int nameIndex=method.getNameIndex();
			int descIndex=method.getDescIndex();
			
			String name=this.getConstantPool().getUTF8String(nameIndex);
			String desc=this.getConstantPool().getUTF8String(descIndex);
					
			if(methodName.equals(name) && paramAndReturnType.equals(desc)){
				return method;
			}
		}
		return null;
	}
	
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
