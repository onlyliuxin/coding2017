package week04.minijvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {
	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) throws IOException {
		if(className == null){
			return null;
		}
		
		boolean isFileExist = false;
		File file = null;
		String classPath = className.replace(".", "\\"); 
		for(int i = 0 ; i < clzPaths.size(); i++){
			String basePath = clzPaths.get(i);
			file = new File(basePath + File.separator + classPath + ".class");
			
			if(file.exists()){
				isFileExist = true;
				break;
			}
		}
		
		//找不到类
		if(!isFileExist){
			throw new FileNotFoundException();
		}
		
		//读取字节码文件到数组
		FileInputStream in = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte [] rs = new byte[1024];
		int len = 0;
		while((len = in.read(rs)) != -1){
			bos.write(rs, 0, len);
		}
		bos.close();
		in.close();
		System.out.println("readBinaryCode:" + " file size = " + file.length());
		return bos.toByteArray();
	}

	public void addClassPath(String path) {
		if(! clzPaths.contains(path)){
			clzPaths.add(path);
		}
	}

	public String getClassPath() {
		StringBuffer buffer = new StringBuffer();
		for(int i = 0;i < clzPaths.size();i++){
			buffer.append(clzPaths.get(i));
			if(i != clzPaths.size() - 1){
				buffer.append(";");
			}
		}
		return buffer.toString();
	}
}
