package task4.jvm.loader;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) throws Exception {
		String filePath = clzPaths.get(0)+File.separatorChar+className.replace('.',File.separatorChar)+".class";
		File file = new File(filePath);
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[1024];
		int len = 0;
		while((len = bis.read(buffer)) != -1){
			baos.write(buffer,0,len);
		}
		return baos.toByteArray();		
	}
	
	public void addClassPath(String path) {
		if (clzPaths.contains(path))
			return;
		clzPaths.add(path);
	}
		
	public String getClassPath(){
		return StringUtils.join(clzPaths,";");
	}

}
