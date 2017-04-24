package com.coderising.jvm.constant;

public class ClassInfo extends ConstantInfo{

	private int type = ConstantInfo.CLASS_INFO;
	private int Utf8Index;
	
	public ClassInfo(ConstantPool constantPool){
		super(constantPool);
	}
	public int getUtf8Index() {
		return Utf8Index;
	}

	public void setUtf8Index(int utf8Index) {
		Utf8Index = utf8Index;
	}
	
	public String getClassName(){
		Utf8Info utf8Info = (Utf8Info) this.constantPool.getConstantInfo(Utf8Index);
		return utf8Info.getValue();
	}

	@Override
	public int getType() {
		return this.type;
	}

}
