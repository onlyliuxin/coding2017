package lilei.com.cn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * ReadFile 读取文件类  
 * 个人理解， 唯一能引发变化的就是文件路径的变动
 * 
 * */
public class ReadFile {
	
	private File file;
	private String filePath = "F:\\product_promotion.txt";
	
	protected String[] readFile() throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			file = new File(filePath);
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			return data;

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
}
