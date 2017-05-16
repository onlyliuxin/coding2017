package basic.dataStructure.binaryTree;

import java.io.File;

/**
 * 给定一个目录，递归的列出下面所有的子目录和文件
 *
 */
public class FileList {

	public void list(File f, int level) {
		if(!f.exists()){
			throw new RuntimeException("file " + f.getAbsolutePath() + " not existed");
		}

		StringBuilder head = new StringBuilder();
		head.append("|--").append(f.getName());
		System.out.println(head.toString());

		File[] files = f.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				printDirectory(file, level + 1);
			}else{
				printFile(file, level);
			}
		}

	}

	private void printDirectory(File f, int level){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < level; i++){
			builder.append("	");
		}
		builder.append("|--").append(f.getName());
		System.out.println(builder.toString());

		File[] files = f.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				printDirectory(file, level + 1);
			}else{
				printFile(file, level);
			}
		}
	}

	private void printFile(File f, int level){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < level + 1; i++){
			builder.append("	");
		}
		builder.append("|->").append(f.getName());
		System.out.println(builder.toString());
	}
}
