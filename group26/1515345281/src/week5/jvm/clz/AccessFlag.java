package week5.jvm.clz;

public class AccessFlag {

	private int flagValue;

	public AccessFlag(int value){
		this.flagValue=value;
	}
	
	public boolean isPublicClass(){
		return (flagValue & 0x0001) != 0;
	}
	
	public boolean isFinalClass(){
		return (flagValue & 0x0010) != 0;
	}
	
	public int getFlagValue() {
		return flagValue;
	}

	public void setFlagValue(int flagValue) {
		this.flagValue = flagValue;
	}
	
}
