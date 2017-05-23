package tree;

import java.io.File;

import org.junit.Test;

public class FileList {
	
	public void list(File f) {
		if(f.isFile()){
			System.out.println(f.getAbsolutePath());
			return ;
		}
		
		File[] files = f.listFiles();
		for(File file : files){
			if(file.isFile()){
				System.out.println(file.getAbsolutePath());
			}else{
				list(file);
			}
			
		}
	}
	
	@Test
	public void testList(){
		File file = new File("d:\\Sql Files");
		list(file);
	}

	
}
