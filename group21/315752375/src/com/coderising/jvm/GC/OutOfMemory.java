package com.coderising.jvm.GC;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count=0;
		List<byte[]>tmpList=new ArrayList<>();
		while(true){
			tmpList.add(new byte[1024*1024]);
			count++;
			System.out.println("count:"+count);
		}
	}

}
