package arraylist;

import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {
	ArrayList arr = null;

	@Before
	public void setup() {
		arr = new ArrayList();
	}

	@Test
	public void testAdd() {
		arr.add("ele-1");
		arr.add("ele-2");
		printArrayList();
		arr.add(0, "ele-0");
		printArrayList();
	}

    @Test
	public void testGet() {
		arr.add("ele-1");
		arr.add("ele-2");
		printArrayList();
		arr.remove(0);
		printArrayList();
		System.out.println((String)arr.get(1));
	}

    @Test
	public void testRemove() {

		for (int i = 0; i < 10; i++) {
			arr.add("ele-"+i);
		}

		printArrayList();
		arr.remove(1);
		System.out.println("After Remove");
		printArrayList();
	}

	/**
	 * 打印ArrayList
	 * @param arr
	 */
	private void printArrayList() {
		System.out.print("[");
		for (int i = 0; i < arr.size(); i++) {
			System.out.print((String)arr.get(i) + ", ");
		}
		System.out.println("]");
	}
}
