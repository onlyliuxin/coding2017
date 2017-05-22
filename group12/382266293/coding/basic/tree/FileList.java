package tree;

import java.io.File;

public class FileList {
	
	public void list(File f) {
		
		StringBuilder sb = new StringBuilder("File List: \r\n");
		
		listFile(f, sb, 0);
		
		System.out.println(sb.toString());
		
	}

	private void listFile(File f, StringBuilder sb, int level) {
		
		if (f.isDirectory()) {
			
			for (int i = 0; i < level; i++) {
				sb.append("--");
			}
			
			level++;
			
			sb.append("Dir:" + f.getName() + "\r\n");
			for(File f1: f.listFiles()) {
				listFile(f1,sb,level);
			}

		} else {
			
			for (int i = 0; i <= level; i++) {
				sb.append("--");
			}

			sb.append(f.getName() + "\r\n");
		}

	}

	public static void main(String[] args) {
		FileList ff = new FileList();
		ff.list(new File("D:\\py"));
	}
}
