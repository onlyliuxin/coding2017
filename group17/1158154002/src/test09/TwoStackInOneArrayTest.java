package test09;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwoStackInOneArrayTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() {
		
		//Assert.assertEquals("[1, 3, 5, 0, 4, 2, 6]", Josephus.execute(7, 2).toString());
		TwoStackInOneArray two=new TwoStackInOneArray();
		two.push1(1);
		two.push1(2);
		two.push1(3);
		two.push1(4);
		two.push1(5);
		two.push1(6);

		
		two.push2(8);
		two.push2(7);
		two.push2(6);
		two.push2(5);
		
		System.out.println(two.first+":"+two.end);
		two.print();
	}
}
