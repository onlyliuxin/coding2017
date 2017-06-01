package org.le.d;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		File file = null;
		final String classAllName = className.replace(".", File.separator) + ".class";
		for(String path : clzPaths){
			File filePath = new File(path);
			if(filePath.isDirectory()){
				File[] fileList = filePath.listFiles(new FilenameFilter(){
					@Override
					public boolean accept(File dir, String name) {
						String filePath = dir.getPath()+File.separator+name;
						return filePath.contains(classAllName);
					}
				});
				if(fileList.length > 0){
					file = fileList[0];
					break;
				}
			}
		}
		if(file != null){
			try (
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					){
				byte[] buf = new byte[(int) file.length()];
				in.readFully(buf);
				return buf;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;	
		
		
	}
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuilder str = new StringBuilder();
		for(String s : clzPaths){
			str.append(';').append(s);
		}
		return str.length() > 0 ?str.substring(1):"";
	}

	

	

}
