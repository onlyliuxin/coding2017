package weeks10.test;

import java.io.File;

import org.junit.Test;

import weeks10.FileList;

public class FileListTest {

	@Test
	public void testFileList(){
		FileList list=new FileList();
		list.list(new File("e://文件夹1"));
	}
}
