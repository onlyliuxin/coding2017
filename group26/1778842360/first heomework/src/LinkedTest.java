
//package com.coding.basic;

public class LinkedTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     LinkedList l=new LinkedList();
     //ÔªËØÁÐ±í  world me hello add java
     l.add("hello");
     l.addFirst("world");
     l.add(2,"me");
     l.add("add");
     l.addLast("java");
     Iterator it=l.iterator();
     //System.out.println(1);
     while(it.hasNext())
     {
    	 System.out.print(it.next()+" ");
     }
     System.out.println(l.get(5));
     System.out.println(l.removeFirst());
     System.out.println(l.removeFirst());
     System.out.println(l.removeLast());
     //l.remove(2);
     System.out.println(l.remove(1));
     System.out.println(l.remove(2));
     System.out.println(l.size());
	}

}
