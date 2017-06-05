package assignment0326.jvm.constant;

public class NullConstantInfo extends ConstantInfo {

	public NullConstantInfo(){
		
	}
	@Override
	public int getType() {		
		return -1;
	}

	@Override
	public String typeDescription() {
		return "NullConstant";
	}

	@Override
	public String contentDescription() {
		return "null";
	}

}
