package com.coderising.jvm.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// not working for JDK 1.8
public class PermOOMTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();    
        while(true){    
                list.add(UUID.randomUUID().toString().intern());    
        }    
	}
}
