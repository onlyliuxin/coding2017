package task0514.coding.basic.tree;

import java.io.File;

public class FileList {
	public void list(File f) {
		if (null == f) {
			throw new RuntimeException("the File is null");
		}
		if (f.isDirectory()) {
			System.out.println(f.getName());
			File[] fileList = f.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				list(file);
			}
		} else {
			System.out.println(f.getName());
		}
	}
	public static void main(String[] args) {
		FileList fl = new FileList();
		fl.list(new File("E:\\Downloads\\WinToGo"));
	}
}

