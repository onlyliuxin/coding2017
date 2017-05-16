package com.github.ipk2015.coding2017.basic.tree;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileListTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testList() {
		String filePath = "E:\\javaImprove\\git\\group24\\121111914\\src\\com\\github\\ipk2015\\coding2017\\basic";
		File f = new File(filePath);
		FileList.list(f);
		assertEquals(1, 1);
	}

}
