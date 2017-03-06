package com.github.congcongcong250.coding2017.basicTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public interface testCase {
	@Before
	public void setUp();
	
	@After
	public void tearDown();
	
	@Test
	public void testAdd();
	
	@Test
	public void testRemove();
	
	@Test
	public void testFunctional();
}
