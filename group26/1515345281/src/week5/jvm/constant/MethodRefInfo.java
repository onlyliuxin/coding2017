package week5.jvm.constant;

public class MethodRefInfo extends ConstantInfo{

	private int type=ConstantInfo.METHOD_INFO;
	private int classInfoIndex;
	private int nameAndTypeIndex;
	
	public MethodRefInfo(ConstantPool pool){
		super(pool);
	}
	
	public String getClassName(){
		ClassInfo clzInfo = (ClassInfo)this.getConstantInfo(this.getClassInfoIndex());
		return clzInfo.getClassName();
	}
	
	public String getMethodName(){
		NameAndTypeInfo  typeInfo = (NameAndTypeInfo)this.getConstantInfo(this.getNameAndTypeIndex());
		return typeInfo.getName();
	}
	
	public String getParamAndReturnType(){
		ConstantPool pool = this.getConstantPool();
		NameAndTypeInfo  typeInfo = (NameAndTypeInfo)pool.getConstantInfo(this.getNameAndTypeIndex());
		return typeInfo.getTypeInfo();
	}
	
	@Override
	public String toString(){
		return "("+getClassName()+","+getMethodName()+","+getParamAndReturnType()+")";
	}
	
	@Override
	public int getType(){
		return type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitMethodRef(this);
	}
	

	public int getClassInfoIndex() {
		return classInfoIndex;
	}

	public void setClassInfoIndex(int classInfoIndex) {
		this.classInfoIndex = classInfoIndex;
	}

	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}

	public void setNameAndTypeIndex(int nameAndTypeIndex) {
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
}
