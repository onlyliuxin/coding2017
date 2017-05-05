package MyLinkedListTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ralf.linkedlist.MyLinkedList;

public class MyLinkedListTest {

	private MyLinkedList<Integer> list = new MyLinkedList<>();
	@Before
	public void setUp() throws Exception {

		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

	}

	@Test
	public void reverse() {
		
		list.reverse();
		Assert.assertEquals(5, list.size());
		for(int i=0; i<list.size(); i++){
			Assert.assertEquals(5-i, list.get(i).intValue());
		}
	}
	
	@Test
	public void removeFirstHalf(){
		//奇数个元素
		list.removeFirstHalf();
		Assert.assertEquals(3, list.size());

		for(int i=0; i<list.size(); i++){
			Assert.assertEquals(i+3, list.get(i).intValue());
		}
		
		//偶数个元素
		MyLinkedList<Integer> listA = new MyLinkedList<>();
		listA.add(2);
		listA.add(5);
		listA.add(7);
		listA.add(8);
		listA.removeFirstHalf();
		
		Assert.assertEquals(2, listA.size());
		Assert.assertEquals(7, listA.get(0).intValue());
		Assert.assertEquals(8, listA.get(1).intValue());
	
	}
	
	@Test
	public void removeLastHalf(){
		
		list.removeLastHalf();
		Assert.assertEquals(3, list.size());

		for(int i=0; i<list.size(); i++){
			Assert.assertEquals(i+1, list.get(i).intValue());
		}
		
		//偶数个元素
		MyLinkedList<Integer> listA = new MyLinkedList<>();
		listA.add(2);
		listA.add(5);
		listA.add(7);
		listA.add(8);
		listA.removeLastHalf();
		
		Assert.assertEquals(2, listA.size());
		Assert.assertEquals(2, listA.get(0).intValue());
		Assert.assertEquals(5, listA.get(1).intValue());
	}
	
	//remove(int i, int length)
	@Test
	public void remove(){
	
		list.remove(0, 5);
		Assert.assertEquals(0, list.size());
		
		MyLinkedList<Integer> listA = new MyLinkedList<>();
		listA.add(2);
		listA.add(5);
		listA.add(7);
		listA.add(8);
		listA.add(9);
		listA.add(10);

		listA.remove(1, 3);
		Assert.assertEquals(3, listA.size());
		Assert.assertEquals(2, listA.get(0).intValue());
		Assert.assertEquals(9, listA.get(1).intValue());
		Assert.assertEquals(10, listA.get(2).intValue());
	}
	
	
	//int[] getElements(MyLinkedList<T> list)
	@Test
	public void getElements(){
		
		MyLinkedList<Integer> listA = new MyLinkedList<>();
		listA.add(11);
		listA.add(101);
		listA.add(201);
		listA.add(301);
		listA.add(401);
		listA.add(501);
		listA.add(601);
		listA.add(701);
		
		MyLinkedList<Integer> listB = new MyLinkedList<>();
		listB.add(1);
		listB.add(3);
		listB.add(4);
		listB.add(6);
		
		int[] aar;
		aar = listA.getElements(listB);
		Assert.assertEquals(4, aar.length);
		
		Assert.assertEquals(101, aar[0]);
		Assert.assertEquals(301, aar[1]);
		Assert.assertEquals(401, aar[2]);
		Assert.assertEquals(601, aar[3]);
		
	}
	
	
	//subtract(MyLinkedList<T> list)
	@Test
	public void subtract(){
		
		MyLinkedList<Integer> listA = new MyLinkedList<>();
		listA.add(11);
		listA.add(101);
		listA.add(201);
		listA.add(301);
		listA.add(401);
		listA.add(501);
		listA.add(601);
		listA.add(701);
		
		MyLinkedList<Integer> listB = new MyLinkedList<>();
		listB.add(201);
		listB.add(301);
		listB.add(501);
		listB.add(801);
		
		listA.subtract(listB);
		Assert.assertEquals(5, listA.size());
		
		Assert.assertEquals(11, listA.get(0).intValue());
		Assert.assertEquals(101, listA.get(1).intValue());
		Assert.assertEquals(401, listA.get(2).intValue());
		Assert.assertEquals(601, listA.get(3).intValue());
		Assert.assertEquals(701, listA.get(4).intValue());
	}
	
	@Test
	public void removeRepeatValues(){
		//自己的方法，但是是无序的，删除时是删除前面的相同元素
		MyLinkedList<Integer> listA = new MyLinkedList<>();
		listA.add(11);//
		listA.add(101);//
		listA.add(101);
		listA.add(301);//
		listA.add(11);
		listA.add(301);
		listA.add(201);//
		listA.add(701);//
		
		listA.removeRepeatValues();
		Assert.assertEquals(5, listA.size());

		Assert.assertEquals(101, listA.get(0).intValue());
		Assert.assertEquals(11, listA.get(1).intValue());
		Assert.assertEquals(301, listA.get(2).intValue());
		Assert.assertEquals(201, listA.get(3).intValue());
		Assert.assertEquals(701, listA.get(4).intValue());
	}
	
	@Test
	public void removeDuplicateValues(){
		
		MyLinkedList<Integer> listA = new MyLinkedList<>();
		
		listA.add(11);
		listA.add(11);
		listA.add(12);
		listA.add(13);
		listA.add(14);
		listA.add(14);
		listA.add(15);
		listA.add(16);
		listA.add(16);
		
		listA.removeDuplicateValues();
		Assert.assertEquals(6, listA.size());

		Assert.assertEquals(11, listA.get(0).intValue());
		Assert.assertEquals(12, listA.get(1).intValue());
		Assert.assertEquals(13, listA.get(2).intValue());
		Assert.assertEquals(14, listA.get(3).intValue());
		Assert.assertEquals(15, listA.get(4).intValue());
		Assert.assertEquals(16, listA.get(5).intValue());
		
	}
	
	
	//removeRange(int min, int max)
	@Test
	public void removeRange(){
		
		MyLinkedList<Integer> listA = new MyLinkedList<>();
		
		listA.add(11);
		listA.add(11);
		listA.add(12);
		listA.add(13);
		listA.add(14);
		listA.add(14);
		listA.add(15);
		listA.add(16);
		listA.add(16);
		
		listA.removeRange(12, 16);
		
		Assert.assertEquals(5, listA.size());
		Assert.assertEquals(11, listA.get(0).intValue());
		Assert.assertEquals(11, listA.get(1).intValue());
		Assert.assertEquals(12, listA.get(2).intValue());
		Assert.assertEquals(16, listA.get(3).intValue());
		Assert.assertEquals(16, listA.get(4).intValue());
		
	}
	
	@Test
	public void intersection(){
		
		MyLinkedList<Integer> list = new MyLinkedList<>();
		MyLinkedList<Integer> listB = new MyLinkedList<>();
		MyLinkedList<Integer> listC = new MyLinkedList<>();
		
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(17);
		list.add(18);

		listB.add(10);
		listB.add(12);
		listB.add(14);
		listB.add(15);
		listB.add(18);
		
		
		listC = (MyLinkedList<Integer>) list.intersection(listB);
		
		Assert.assertEquals(4, listC.size());
		Assert.assertEquals(12, listC.get(0).intValue());
		Assert.assertEquals(14, listC.get(1).intValue());
		Assert.assertEquals(15, listC.get(2).intValue());
		Assert.assertEquals(18, listC.get(3).intValue());
	}

}
