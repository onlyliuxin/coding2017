package jvm_LRU_170402.coderising.jvm.loader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		byte[] result = null;
		className = className.replace('.', '\\');
		String path = clzPaths.get(0)+"\\"+className+".class";
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(path, "r");	
			int length = (int) raf.length();
			result = new byte[length];
			raf.read(result);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(raf != null){
				try{
					raf.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return result;			
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < clzPaths.size(); i++) {
			if(i==clzPaths.size()-1){
				sb.append(clzPaths.get(i));
				break;
			}
			sb.append(clzPaths.get(i));
			sb.append(";");
		}
		return sb.toString();
	}
}
