package DataStructure_3;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class TestLinkedList {

	@Test
	public void test() {
		LinkedList L = new LinkedList();
		Assert.assertEquals("[]", L.toString());
		
		L.add(1);
		L.reverse();
		Assert.assertEquals("[1]", L.toString());
		
		L.add(2);
		L.add(3);
		L.add(4);
		L.reverse();
		Assert.assertEquals("[4,3,2,1]",L.toString());
	}

}
