package week567_miniJVM.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import week567_miniJVM.clz.ClassFile;

public class ClassFileLoader {
    private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) throws Exception {
		for(String s:clzPaths){
			String filename = s+className+".class";
			File file = new File(filename);
			if(file.exists())return loadClassFile(filename);
		}
		return null;
	}
	public ClassFile loadClass(String className){
        return null;
    }
	private byte[] loadClassFile(String clzFileName) throws Exception {
		File file = new File(clzFileName);
		long filelength = file.length();
		byte[]res = null;
		if(filelength>Integer.MAX_VALUE)throw new IOException("文件过大");
		try {
			FileInputStream fileinput = new FileInputStream(file);
			res = new byte[(int) filelength];
			int offset=0,length=0;
			while(offset<res.length && ((length=fileinput.read(res,offset,res.length-offset))>-1))
				offset += length;
			fileinput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	public String getClassPath(){
		String res = "";
		int size = clzPaths.size();
		for(int i=0;i<size-1;i++){
			res += clzPaths.get(i);
			res += ";";
		}
		res += clzPaths.get(size-1);
		return  res;
	}
}
