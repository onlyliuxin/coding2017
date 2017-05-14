package test10;

import java.io.File;

public class FileList {
	
	public void list(File f) {
		//如果是目录继续，是文件则打印
		String path="";
		if (f.isDirectory()) {
			File[] files=f.listFiles();
			for (File file : files) {
				list(file);
			}
		} else {
			System.out.println(f.getName());
		}
	}
}
