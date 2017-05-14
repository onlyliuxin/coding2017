package dataStructure_10_BinaryTree;

import java.io.File;

public class FileList {
	
	public void list(File f) {	
	
		File[] file = f.listFiles();
		for (File file2 : file) {
			if(file2.isDirectory()){
				list(file2);
			}else{
				System.out.println(file2);
			}
		}
	}
	
	public static void main(String[] args) {
		new FileList().list(new File("D:\\Test"));
	}
	
}
