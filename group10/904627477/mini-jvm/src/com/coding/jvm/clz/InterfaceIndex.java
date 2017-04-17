package com.coding.jvm.clz;

import java.util.ArrayList;
import java.util.List;

public class InterfaceIndex {
	
	private List<Integer> interfaceIndexs = new ArrayList<Integer>();

	public List<Integer> getInterfaceIndexs() {
		return interfaceIndexs;
	}

	public InterfaceIndex() {
		super();
	}

	public void addInterfaceIndex(int interfaceIndex) {
		interfaceIndexs.add(interfaceIndex);
	}
	
	

}
