package com.dudy.learn01.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

public class MyArrayListTest {



    @Test
    public  void iteraterTest(){
        MyArrayList list = new MyArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }


        for(MyIterator it = list.iterator(); it.hasNext();){
            System.out.print(it.next() + " ");
        }


    }

	@Test
	public void myArrayListTest() {
		MyArrayList list = new MyArrayList();
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}

		list.add(1, "s");
		list.add(21, 21);
		System.out.println("--" + list.size());
		System.out.println(list);

		Object remove = list.remove(3);
		System.out.println("remove:" + remove);
		System.out.println("--" + list.size());
		System.out.println(list);
	}

	@Test
	public  void  arrayListTest(){

        ArrayList<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");

        for (Iterator<String> it = list.iterator(); it.hasNext();){
            System.out.println(it.next());
        }

    }


}
