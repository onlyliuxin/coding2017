package junittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import basicstruct.ArrayList;

public class ArraylistTest {
	
	
	ArrayList list = new ArrayList();
	@Before
	public void setUp() throws Exception {
		list.add(1);
		list.add(1,"boy next door");
		list.add(222);
		list.add("333");
		list.add(4,"444");
		list.add(1232);
		list.add("555");
	}


	@Test
	public void testAdd() {
		System.out.println(list.get(0));
		System.out.println(list.get(3));
		System.out.println(list.get(5));
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		list.remove(0);
		list.remove(5);
		System.out.println(list.get(0));
		System.out.println(list.size());
//		System.out.println(list.get(5));
	}

	@Test
	public void testSize() {
		System.out.println(list.size());
	}

}
