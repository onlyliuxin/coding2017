package miniJvm.com.coderising.jvm.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {

		String fullClassName = className.replace(".", "\\") + ".class";
		for (String clzpath : clzPaths){
			byte[] binaryCode = readBinaryCode(clzpath, fullClassName);
			if (binaryCode != null){
				return binaryCode;
			}
		}
		return null;
	}

	private byte[] readBinaryCode(String clzPath, String fullClassName){

		String filePath = clzPath + "\\" +  fullClassName;
		File classFile = new File(filePath);
		if (!classFile.exists()){
			return null;
		}
		try {
			FileInputStream fileInputStream = new FileInputStream(classFile);
			DataInputStream dataInputStream = new DataInputStream(fileInputStream);
			List<Byte> bytes = new ArrayList<Byte>();
			int b;
			while ((b = dataInputStream.read()) != -1){
				bytes.add((byte)b);
			}
			byte[] res = new byte[bytes.size()];
			for (int i = 0; i < bytes.size(); i++){
				res[i] = bytes.get(i);
			}
			return res;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addClassPath(String path) {

		if (path == null){
			return;
		}
		clzPaths.add(path);
	}

	public String getClassPath(){

		StringBuffer stringBuffer = new StringBuffer();
		for (String path : clzPaths){
			stringBuffer.append(path).append(";");
		}
		return stringBuffer.substring(0, stringBuffer.length() - 1);
	}

}
