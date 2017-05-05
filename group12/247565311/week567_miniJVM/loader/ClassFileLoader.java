package week567_miniJVM.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import week567_miniJVM.clz.ClassFile;

public class ClassFileLoader {
    private List<String> clzPaths = new ArrayList<String>();
	
	public ClassFile loadClass(String className){
        
        byte[] bytes = readBinaryCode(className);
        ClassFile clzFile = new ClassFileParser().parse(bytes);
        
        return clzFile;
    }
	public byte[] readBinaryCode(String className) {
		for(String s:clzPaths){
			String filename = s+className+".class";
			File file = new File(filename);
			if(file.exists())return loadClassFile(filename);
		}
		return null;
	}
	private byte[] loadClassFile(String clzFileName) {
		File file = new File(clzFileName);
		long filelength = file.length();
		byte[]res = null;
		if(filelength>Integer.MAX_VALUE) new IOException("类文件过大！").printStackTrace();
		try {
			FileInputStream fileinput = new FileInputStream(file);
			res = new byte[(int) filelength];
			int offset=0,length=0;
			while(offset<res.length && ((length=fileinput.read(res,offset,res.length-offset))>-1))
				offset += length;
			if(fileinput!=null)fileinput.close();
		} catch (Exception e) {
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
