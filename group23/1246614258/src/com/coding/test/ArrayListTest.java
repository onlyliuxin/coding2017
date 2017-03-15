package com.coding.test;
import com.coding.datastructs.ArrayList;
import com.coding.datastructs.Iterator;


public class ArrayListTest {

	/**
	 * <p>Description:</p>
	 * @param args
	 * @author:Wilson huang
	 * @date 2017-3-12обнГ12:08:10
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList aa = new ArrayList();
		aa.add("aa");
		aa.add("bb");
		aa.add("cc");
		//aa.remove(3);
		aa.add(2, "44");
		for(int i=0;i<aa.size();i++){
			System.out.println(aa.get(i));
		}
		Iterator it = aa.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}

	}

}
