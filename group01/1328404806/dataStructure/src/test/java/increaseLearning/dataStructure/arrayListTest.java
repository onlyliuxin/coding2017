package increaseLearning.dataStructure;


import org.junit.Test;

import ListServiceImpl.KArrayList;
import junit.framework.TestCase;

public class arrayListTest extends TestCase {
	@Test
	public void testArrayList() {

		KArrayList<Integer> arr = new KArrayList<Integer>();

		for (int i = 1; i <= 50; i++) {
			arr.add(i);
		}

		arr.add(10, 99);
		arr.add(0, 99);

		System.out.println(arr.get(51));
		System.out.println(arr.get(11));

		// arr.clear();

		// System.out.println(arr.contains(99));
		// System.out.println(arr.indexOf(59));

		arr.remove(11);

		arr = null;

	}

}
