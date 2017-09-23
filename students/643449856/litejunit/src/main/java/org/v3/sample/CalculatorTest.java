package org.v3.sample;

import org.v3.After;
import org.v3.AfterClass;
import org.v3.Before;
import org.v3.BeforeClass;
import org.v3.Test;
import org.v3.runner.JUnitCore;
import static org.v3.Assert.assertEquals;

/**
 * @author neng
 *
 */
public class CalculatorTest  {	
	
	Calculator calculator =null;
	@Before
	public void prepare(){
		calculator = new Calculator();
	}
	@After
	public void clean(){
		calculator = null;
	}
	@Test
	public void testAdd(){
		
		calculator.add(10);
		assertEquals(15,calculator.getResult());
	}	
	@Test
	public void testSubtract(){
		calculator.add(10);
		calculator.subtract(5);
		assertEquals(5,calculator.getResult());
	}
	@BeforeClass
	public static void prepareGlobalResouce(){
		System.err.println("准备所有的资源");
	}
	@AfterClass
	public static void cleanGlobalResouce(){
		System.err.println("清楚所有的资源");
	}
	
	
	public static void main(String[] args){
		JUnitCore.runClass(CalculatorTest.class);
	
	}
}
