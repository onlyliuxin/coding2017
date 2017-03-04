package com.coding.basic;

public class Test1 {
 public static void main (String[] args){
//	ArrayList arr = new ArrayList();
//	arr.add(1);
//	arr.add(2);
//	arr.add(3);
//	arr.add(4);
//	arr.remove(3);
//	arr.add(6);
//	arr.add(7);
//	System.out.println(arr.size()+"_");
//	Iterator it =arr.iterator();
//	while(it.hasNext()){
//	System.out.println(it.next());
//	}
//	 LinkedList link = new LinkedList();
//	 link.add(0);
//	 link.add(1);
//	 link.add(2);
//	 link.add(3);
//	 link.add(4);
//	 link.add(5);
//	 link.remove(2);
//	 link.addFirst(100);
//	 link.addLast(200);
//	 //link.removeFirst();
//	 //link.removeLast();
////	 System.out.println(link.size()+"_");
////	 System.out.println(link.get(0));
////	System.out.println(link.get(link.size()-1));
////	
//
//	Iterator it =link.iterator();
//	while(it.hasNext()){
//	System.out.println(it.next());
//	}
	 Stack st = new Stack();
	 st.push(0);
	 st.push(1);
	 st.push(2);
	 st.push(4);
	 st.push(5);
	 st.pop();
	 System.out.println(st.peek());
 }
}