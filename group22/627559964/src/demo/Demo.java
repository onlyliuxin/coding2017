package demo;

import com.coding.basic.ArrayList;
import com.coding.basic.Iterator;
import com.coding.basic.List;

public class Demo {
	public static void main(String[] args) {
		
		List list = new ArrayList();
		for (int i = 0; i < 12; i++) {
			list.add(new Integer(123));
		}
		list.add(3, new Integer(233));
		list.add(3, new Double(233.33));
		list.remove(6);
		Double kk = (Double) list.get(3);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("End");
	}
}
