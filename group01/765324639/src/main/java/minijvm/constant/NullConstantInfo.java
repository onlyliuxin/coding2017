package minijvm.constant;

public class NullConstantInfo extends ConstantInfo {

	public NullConstantInfo(){
		
	}
	@Override
	public int getType() {		
		return -1;
	}
    @Override
    public void accept(Visitor visitor) {
        // 占位类，不需访问
        throw new RuntimeException("此类不应访问到，代码中应该有问题");
    }
	
}
