package basic.tree;

import java.io.File;

/**
 * 给定一个目录，递归的列出下面所有的子目录和文件
 */
public class FileList {
	public static void list(File f) {
		if(f.isDirectory()){
			File[] files = f.listFiles();
			for(File file : files){
				if(file.isDirectory()){
					System.out.println(file.getName());
					list(file);
				}else{
					System.out.println(file.getName());
				}
			}
		}
	}

	public static void main(String[] args) {
		File f = new File("C:\\Users\\zhouliang\\Desktop\\其他");
		list(f);
	}
}
