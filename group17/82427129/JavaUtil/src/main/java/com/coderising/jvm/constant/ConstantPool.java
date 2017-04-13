package com.coderising.jvm.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {
	private List<ConstantInfo> cl = new ArrayList<ConstantInfo>();

	public void addConstantInfo(ConstantInfo e){
		cl.add(e);
	}

	public ConstantInfo getConstantInfo(int index) {
		return cl.get(index);
	}

	public int getSize() {
		return cl.size() - 1;// 减去常量池的长度一项
	}

	public String getUTF8String(int nameIndex) {
		return ((UTF8Info)getConstantInfo(nameIndex)).getValue();
	}
	
	public String print(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cl.size(); i++) {
			ConstantInfo c = cl.get(i);
			if(i<10){
				System.out.print("0"+i+". ");
			}else{
				System.out.print(i+". ");
			}
			c.print();
		}
		return sb.toString();
	}
}
