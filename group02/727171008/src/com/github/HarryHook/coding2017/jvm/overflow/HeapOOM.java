package com.github.HarryHook.coding2017.jvm.overflow;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
    
    static class OOMObject {
	
    }
    public static void main(String[] args) {
	List<OOMObject> list = new ArrayList<OOMObject>();
	while(true) {
	    list.add(new OOMObject());
	}
    }
   
}