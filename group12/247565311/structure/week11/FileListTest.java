package structure.week11;

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
		String path = "F:\\books";
		File f = new File(path);
		new FileList().list(f);
	}

}
