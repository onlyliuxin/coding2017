package com.github.HarryHook.coding2017.jvm.overflow;

import java.util.*;

/*
 * VM Args: -XX PermSize=10M -XX MaxPermSize=10M
 */
public class RunntimeConstantPoolOOM {
    public static void main(String[] args) {
	List<String> list = new ArrayList<String>();
	int i = 0;
	while(true) {
	    list.add(String.valueOf(i++).intern());
	}
    }
}
