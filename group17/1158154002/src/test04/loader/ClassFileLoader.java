package test04.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;


public class ClassFileLoader {
	private List<String> clzPaths = new ArrayList<String>();
	
	
	public byte[] readBinaryCode(String className) {
		className=className.replace(".", File.separator)+".class";
		for (String path : clzPaths) {
			String clzFileName=path+File.separator+className;
			byte[] codes=loadClassFile(clzFileName);
			if (codes!=null) {
				return codes;
			}
		}
		return null;	
		
		
	}
	
	private byte[] loadClassFile(String clzFileName) {
		File file=new File(clzFileName);
		try {
			return IOUtils.toByteArray(new FileInputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	
	
	public void addClassPath(String path) {
		if (this.clzPaths.contains(path)) {
			return;
		}
		this.clzPaths.add(path);
	}
	
	public String getClassPath_V1(){
		
		return  null;
	}
	
	public String getClassPath(){
		return StringUtils.join(this.clzPaths,";");
	}
}
