package dataStructure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zj on 2017/2/20.
 */
public class ArrayListTest {
	ArrayList list =null;


	@Before
	public void setUp() throws Exception {
		list = new ArrayList();
		for (int i = 0; i < 200; i++) {
			list.add(i);
		}

		System.out.println("=============================before==============================");
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=" + list.get(i + 1));
			} catch (Exception e) {
				System.out.println("index=" + i + ",data=" + list.get(i) + ",next=null");
			} finally {

			}

		}
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("=============================after==============================");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + ",");
		}
	}

	@Test
	public void add() throws Exception {
	}

	@Test
	public void add1() throws Exception {
		list.add(5, 555);
		list.add(12, 1255);
	}

	@Test
	public void get() throws Exception {

	}

	@Test
	public void remove() throws Exception {
		list.remove(3);
		list.remove(90);
	}

	@Test
	public void size() throws Exception {
	}

	@Test
	public void iterator() throws Exception {

		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + ",");
		}

		System.out.println();
		System.out.println("---------------------------");
	}

}