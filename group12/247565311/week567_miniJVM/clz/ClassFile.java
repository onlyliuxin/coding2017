package week567_miniJVM.clz;

import week567_miniJVM.constant.ClassInfo;
import week567_miniJVM.constant.ConstantPool;
import week567_miniJVM.field.Field;
import week567_miniJVM.method.Method;
import structure.week1.ArrayList;

public class ClassFile {
    public int minorVersion,majorVersion;
	private AccessFlag accessFlag = null;
	private ClassIndex clzIndex = null;
    private ConstantPool constPool = null;
    private ArrayList<Method> methods = null;
    private ArrayList<Field> fields = null;
    
	public ClassIndex getClzIndex() {
		return clzIndex;
	}
    public void setClzIndex(ClassIndex clz){
        clzIndex = clz;
    }
	public AccessFlag getAccessFlag() {
		return accessFlag;
	}
    public void setAccessFlag(AccessFlag acsFlag){
        accessFlag = acsFlag;
    }
	public ConstantPool getConstantPool() {
		return constPool;
	}
    public void setConstantPool(ConstantPool pool){
        constPool = pool;
    }
	public int getMinorVersion() {
		return minorVersion;
	}
	public int getMajorVersion() {
		return majorVersion;
	}
    public void setVersion(int minor,int major){
        minorVersion = minor;
        majorVersion = major;
    }
	public void print(){
		if(this.accessFlag.isPublicClass()){
			System.out.println("Access flag:public");
		}
		System.out.println("Class Name:"+ getClassName());
		System.out.println("Super Class Name:"+ getSuperClassName());
	}
	private String getClassName(){
		int thisClassIndex = this.clzIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo)this.getConstantPool().getConstantInfo(thisClassIndex);
		return thisClass.getClassName();
	}
	private String getSuperClassName(){
		ClassInfo superClass = (ClassInfo)this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}
	public ArrayList<Method> getMethods() {
		return methods;
	}
	public ArrayList<Field> getFields() {
		return fields;
	}
	public void setClassIndex(ClassIndex parseClassIndex) {
		clzIndex = parseClassIndex;
	}
	public void setFields(ArrayList<Field> parseFields) {
		fields = parseFields;
	}
	public void setMethods(ArrayList<Method> parseMethods) {
        methods = parseMethods;
	}
}
