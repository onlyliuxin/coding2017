package week5.jvm.constant;

public class ClassInfo extends ConstantInfo {

	private int type=ConstantInfo.CLASS_INFO;
	private int utf8Index;

	public ClassInfo(ConstantPool pool){
		super(pool);
	}
	
	public String getClassName(){
		int index=getUtf8Index();
		UTF8Info info=(UTF8Info) constantPool.getConstantInfo(index);
		return info.getValue();
	}
	
	@Override
	public int getType() {
		return this.type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitClassInfo(this);	
	}

	public int getUtf8Index() {
		return utf8Index;
	}

	public void setUtf8Index(int utfIndex) {
		this.utf8Index = utfIndex;
	}
}
