package org.wsc.coderising.jvm.clz;

/**
 *
 * 类及父类索引
 * @author Administrator
 * @date 2017年4月9日下午2:39:45
 * @version v1.0
 *
 */
public class ClassIndex {
	/** 类索引 */
	private int thisClassIndex;
	/** 父类索引 */
	private int superClassIndex;

	public int getThisClassIndex() {
		return thisClassIndex;
	}

	public void setThisClassIndex(int thisClassIndex) {
		this.thisClassIndex = thisClassIndex;
	}

	public int getSuperClassIndex() {
		return superClassIndex;
	}

	public void setSuperClassIndex(int superClassIndex) {
		this.superClassIndex = superClassIndex;
	}
}
