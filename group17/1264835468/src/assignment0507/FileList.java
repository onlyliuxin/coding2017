package assignment0507;

import java.io.File;

public class FileList {
	public void list(File f) {
		for (File file : f.listFiles()) {
			if (file.isFile()) {
				System.out.println(file);
			}else {
				list(file);
			}
		}
	}

	public static void main(String[] args) {
		FileList fileList = new FileList();
		fileList.list(new File("src"));
	}
}
