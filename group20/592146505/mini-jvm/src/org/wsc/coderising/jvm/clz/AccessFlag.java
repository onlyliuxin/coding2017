package org.wsc.coderising.jvm.clz;

/**
 *
 * 访问标志
 * @author Administrator
 * @date 2017年4月9日下午2:24:21
 * @version v1.0
 *
 */
public class AccessFlag {
	
	private int flagValue;

	public AccessFlag(int value) {
		this.flagValue = value;
	}

	public int getFlagValue() {
		return flagValue;
	}

	public void setFlagValue(int flag) {
		this.flagValue = flag;
	}

	/**
	 * 是public
	 * @return
	 */
	public boolean isPublicClass() {
		return (this.flagValue & 0x0001) != 0;
	}

	/**
	 * 是final
	 * @return
	 */
	public boolean isFinalClass() {
		return (this.flagValue & 0x0010) != 0;
	}
}
