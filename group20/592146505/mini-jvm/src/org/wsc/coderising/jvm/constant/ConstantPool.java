package org.wsc.coderising.jvm.constant;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 常量池
 * @author Administrator
 * @date 2017年4月9日下午2:53:16
 * @version v1.0
 *
 */
public class ConstantPool {
	private List<ConstantInfo> constantInfos = new ArrayList<ConstantInfo>();

	public ConstantPool() {

	}

	public void addConstantInfo(ConstantInfo info) {

		this.constantInfos.add(info);

	}

	public ConstantInfo getConstantInfo(int index) {
		return this.constantInfos.get(index);
	}

	public String getUTF8String(int index) {
		return ((UTF8Info) this.constantInfos.get(index)).getValue();
	}

	public Object getSize() {
		return this.constantInfos.size() - 1;
	}
}
