package test10;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test09.QuickMinStack;

public class FileListTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() {
		FileList file=new FileList();
		file.list(new File("C:/Users/Administrator/Desktop/html"));
	}
}
