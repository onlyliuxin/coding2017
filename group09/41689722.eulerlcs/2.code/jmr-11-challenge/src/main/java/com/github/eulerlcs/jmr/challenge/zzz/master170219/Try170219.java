package com.github.eulerlcs.jmr.challenge.zzz.master170219;

import java.util.ArrayList;
import java.util.Date;

public class Try170219 {
	public static void main(String[] args) {
		Integer[] a = new Integer[10];
		case003(a);
	}

	public static void case001() {
		Fruit f = new Apple();
		f.setDate(new Date());
	}

	public static void case002() {
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		System.out.println(list1.getClass().equals(list2.getClass()));
	}

	public static void case003(Number[] n) {
		// nop
	}

}

class Fruit {
	public void setDate(Object d) {
		System.out.println("Fruit.setDate(Object d)");
	}

	// public void setDate2(Object d) {
	// System.out.println("Fruit.setDate(Object d)");
	// }
}

class Apple extends Fruit {
	public void setDate(Date d) {
		System.out.println("Apple.setDate(Date d)");
	}

	public void setDate2(Date d) {
		System.out.println("Apple.setDate(Date d)");
	}
}