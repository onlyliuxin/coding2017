package com.coderising.jvm.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coderising.jvm.loader.ByteCodeIterator;

public class ByteCodeIteratorTest {

	ByteCodeIterator iter = null;
	@Before
	public void setUp() throws Exception {
		byte[] source = new byte[]{127,2, 16, 10,11};
		iter = new ByteCodeIterator(source);
		
		
	}
	
	@Test
	public void testPrintByteNumber(){
		byte b = 21;
		int c = 52;
		ByteCodeIterator.printByteInNumber(b);
		ByteCodeIterator.printByteInNumber((byte)c);
		
	}
	
	@Test
	public void testGetNextByte() {
		assertEquals("7f", iter.getNextHexString());
		assertEquals("2", iter.getNextHexString());
		assertEquals("10", iter.getNextHexString());
	}

	@Test
	public void testGetNextNBytes() {
		byte[] expected = new byte[]{127,2};
		assertArrayEquals(expected, iter.getNextNBytes(2));
		expected = new byte[]{16, 10,11};
		assertArrayEquals(expected, iter.getNextNBytes(3));
	}

	@Test
	public void testGetByteAt() {
		byte exp = 10;
		assertEquals(exp, iter.getByteAt(3));
	}

}
