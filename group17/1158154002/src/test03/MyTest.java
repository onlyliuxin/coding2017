package test03;

import java.util.Arrays;

import org.junit.Test;

public class MyTest {

	@Test
	public void reverse(){
		LinkedList list=new LinkedList();
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(10);
		list.add(11);	
		
		list.reverse();	
		list.toString();
	}
	
	@Test
	public void removeFirstHalf(){
		LinkedList list=new LinkedList();
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(10);
		list.add(11);	
		
		list.removeFirstHalf();	
		list.toString();
	}
	
	@Test
	public void remove(){
		LinkedList list=new LinkedList();
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(10);
		list.add(11);	
		
		list.remove(1,5);	
		list.toString();
	}	
	
	@Test
	public void getElements(){
		LinkedList list=new LinkedList();
		// 11->101->201->301->401->501->601->701
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);	
		list.add(501);
		list.add(601);
		list.add(701);
		
		LinkedList listB=new LinkedList();
		listB.add(1);
		listB.add(3);
		listB.add(4);
		listB.add(6);
		
		System.out.println(Arrays.toString(list.getElements(listB)));	
	}
	
	//removeRange
	@Test
	public void subtract(){
		LinkedList list=new LinkedList();
		// 11->101->201->301->401->501->601->701
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);	
		list.add(501);
		list.add(601);
		list.add(701);
		
		LinkedList listB=new LinkedList();
		listB.add(201);
		listB.add(601);
		listB.add(401);
		
		list.subtract(listB);	
		list.toString();
	}
	
	@Test
	public void removeDuplicateValues(){
		LinkedList list=new LinkedList();
		// 11->101->201->301->401->501->601->701
		list.add(11);
		list.add(101);
		list.add(301);
		list.add(301);
		list.add(401);	
		list.add(401);
		list.add(601);
		list.add(701);
		
		list.removeDuplicateValues();	
		list.toString();
	}
	
	//intersection
	@Test
	public void removeRange(){
		LinkedList list=new LinkedList();
		// 11->101->201->301->401->501->601->701
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);	
		list.add(501);
		list.add(601);
		list.add(701);
		
		list.removeRange(800,900);	
		list.toString();
	}
	
	@Test
	public void intersection(){
		LinkedList list=new LinkedList();
		// 11->101->201->301->401->501->601->701
		list.add(11);
		list.add(101);
		list.add(201);
		list.add(301);
		list.add(401);	
		list.add(501);
		list.add(601);
		list.add(701);
		
		LinkedList listB=new LinkedList();
		listB.add(22);
		listB.add(201);
		listB.add(401);
		listB.add(601);
		listB.add(801);
		
		list.intersection(listB).toString();	
	}
}
