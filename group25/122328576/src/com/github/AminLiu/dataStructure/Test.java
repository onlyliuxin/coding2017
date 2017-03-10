package com.github.AminLiu.dataStructure;

public class Test {
	public static void main(String[] args) {
		ArrayList arrayList = new ArrayList();
		arrayList.add("abc");
		arrayList.add("ww");
		arrayList.add("eee");
		System.out.println(arrayList.size());
		System.out.println(arrayList.toString());
		arrayList.add(2, "sdsaddad");
		System.out.println(arrayList.size());
		System.out.println(arrayList.toString());
		arrayList.remove(2);
		System.out.println(arrayList.size());
		System.out.println(arrayList.toString());
		System.out.println(arrayList.size());
		System.out.println(arrayList.get(2));
	}
}
