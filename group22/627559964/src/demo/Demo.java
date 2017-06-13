package demo;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;
import com.coding.basic.List;

public class Demo {
	public static void main(String[] args) {
		
//		List list = new ArrayList();
//		for (int i = 0; i < 12; i++) {
//			list.add(new Integer(123));
//		}
//		list.add(3, new Integer(233));
//		list.add(3, new Double(233.33));
//		list.remove(6);
//		System.out.println("List:" + list);
//		Double kk = (Double) list.get(3);
//		Iterator it = list.iterator();
//		while (it.hasNext()) {
//			System.out.println(it.next());
//		}
		
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(5);
		list.add(0, 0);
		list.add(2, 4);
		
		System.out.println(list);
		
		System.out.println(list.size());
		
		System.out.println(list.get(4));
		
		System.out.println(list.remove(4));
		
		System.out.println(list);
		
		System.out.println(list.reverse());
		
		Iterator it = list.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + "  ");
		}
		System.out.println();
		
		list.removeFirstHalf();
		System.out.println(list);
		
		list.remove(0, 1);
		System.out.println(list);
		
		LinkedList ls = new LinkedList();
		list.add(6);
		list.add(7);
		list.add(8);
		ls.add(1);
		ls.add(2);
		System.out.println(list);
		System.out.println(ls);
		System.out.println(Arrays.toString(list.getElements(ls)));

		ls.add(6);
		list.subtract(ls);
		System.out.println(list);
		
		list.add(1, 6);
//		list.add(1, 6);
		System.out.println(list);
//		list.removeDuplicateValues();
//		System.out.println(list);
		
		System.out.println("End");
		
		list.removeRange(4, 7);
		System.out.println(list);

	}
}
