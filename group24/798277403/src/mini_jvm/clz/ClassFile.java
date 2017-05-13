package mini_jvm.clz;


import mini_jvm.constant.ClassInfo;
import mini_jvm.constant.ConstantPool;
import mini_jvm.field.Field;
import mini_jvm.method.Method;

import java.util.ArrayList;
import java.util.List;

public class ClassFile {
	
	private int minorVersion; //java次版本号
	private int majorVersion;  //java主版本号
	private AccessFlag accessFlag; //访问标志，表示public等或者是接口等，包括类和父类的访问标志
	private ClassIndex clzIndex; //类索引和父类索引，用来确定类的继承关系
	private ConstantPool pool; //常量池，包括常量池总数和所有常量
	private List<Field> fields = new ArrayList<Field>(); //字段
	private List<Method> methods = new ArrayList<Method>(); //方法


	public ClassIndex getClzIndex() {
		return clzIndex;
	}
	public AccessFlag getAccessFlag() {
		return accessFlag;
	}
	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	}
	public ConstantPool getConstantPool() {		
		return pool;
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
	public void setConstPool(ConstantPool pool) {
		this.pool = pool;
		
	}
	public void setClassIndex(ClassIndex clzIndex) {
		this.clzIndex = clzIndex;		
	}
	

	public void print(){
		if(this.accessFlag.isPublicClass()){
			System.out.println("Access flag : public  ");
		}
		System.out.println("Class Name:"+ getClassName());
		System.out.println("Super Class Name:"+ getSuperClassName());
	}

	public String getClassName(){
		int thisClassIndex = this.clzIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo)this.getConstantPool().getConstantInfo(thisClassIndex);
		return thisClass.getClassName();
	}
	public String getSuperClassName(){
		ClassInfo superClass = (ClassInfo)this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}


	public void addField(Field f){
		this.fields.add(f);
	}
	public List<Field> getFields(){
		return this.fields;
	}
	public void addMethod(Method m){
		this.methods.add(m);
	}
	public List<Method> getMethods() {
		return methods;
	}


	//根据方法名和参数获取某个方法
	public Method getMethod(String methodName, String paramAndReturnType){
		for(Method m :methods){
			int nameIndex = m.getNameIndex();
			int descriptionIndex = m.getDescriptorIndex();

			String name = this.getConstantPool().getUTF8String(nameIndex);
			String desc = this.getConstantPool().getUTF8String(descriptionIndex);
			if(name.equals(methodName) && desc.equals(paramAndReturnType)){
				return m;
			}
		}
		return null;
	}
	public Method getMainMethod(){
		return getMethod("main","([Ljava/lang/String;)V");
	}
}
