package com.github.ZhoufeifeiJAVA.coding2017.basic;
public class MyLinkedListTest{
	public static void main(String[] args){
		MyLinkedList al = new MyLinkedList();
		al.add("string0");
		al.add("string1");
		al.add("string2");
		al.add("string3");
		al.add("string4");
		al.add("string5");
		al.add("string6");
		al.add("string7");
		al.add("string8");
		al.add("string9");
		al.add("string10");
		al.add("string11");
		al.add("string12");
		al.add("string13");
		al.add("string14");
		al.add(11,"string10.5");
		sop(al.remove(4));
		sop(al.get(10));
		sop(al.get(11));
		sop(al.get(12));
		sop("the size of al is "+al.size());
		sop("the iterator method used,so the result is as follows:");
		for(Iterator it = al.iterator();it.hasNext();){
			sop(it.next());
		}
	}
	public static void sop(Object o){
		System.out.println(o);
	}
}