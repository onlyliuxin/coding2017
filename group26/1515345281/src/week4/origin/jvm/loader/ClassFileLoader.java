package week4.origin.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	static final int BUFFER_SIZE = 1024;
	private List<String> clzPaths = new ArrayList<String>();

	private String path;
	
	public byte[] readBinaryCode(String className) {
		// "week4.origin.jvm.loader.test.EmployeeV1"
		String fileName = path+File.separator+className.replace(".", File.separator) + ".class";

		FileInputStream in=null;
		BufferedInputStream bis=null;
		ByteArrayOutputStream baos=null;
		
		try {
			in = new FileInputStream(fileName);

			bis = new BufferedInputStream(in, BUFFER_SIZE);

			// 缓冲区会因为数据的不断写入而自动增长
			baos = new ByteArrayOutputStream();

			byte[] buffer = new byte[BUFFER_SIZE];

			int len = 0;

			while ((len = bis.read(buffer)) != -1) {
					baos.write(buffer, 0, len);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bis!=null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return baos.toByteArray();
	}

	public void addClassPath(String path) {
		this.path=path;
	}

	public String getClassPath() {
		return path;
	}
}