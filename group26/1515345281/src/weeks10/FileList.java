package weeks10;
import java.io.File;

//给定一个目录，递归的列出下面所有的子目录和文件
public class FileList {
	
	public  void list(File f) {	
		
		if(!f.exists()){
			throw new RuntimeException("目录不存在");
		}
		
		if(f.isFile()){
			System.out.println(f.getName());
			return ;
		}
		
		if(f.isDirectory()){
			System.out.println(f.getName());
			File[] files=f.listFiles();
			for(File file:files){
				list(file);
			}
		}
	}
}