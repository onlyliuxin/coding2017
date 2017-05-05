
package week04.jvm.loader;

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
		URL base = this.getClass().getResource("/");
		String baseToString = ""+base;
		String filePath = baseToString.replaceAll("file:/", "")+className.replace(".", "\\")+".class";
		//String filePath = clzPaths.get(0)+"\\"+className.replace(".", "\\")+".class";  //符合Junit测试调用addClassPath方法
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
		clzPaths.add(path);
	}
		
	public String getClassPath(){
		StringBuffer strBuffer = new StringBuffer();
		for(int i=0;i<clzPaths.size();i++){
			if(i == (clzPaths.size() - 1)){
				strBuffer.append(clzPaths.get(i));
			}else{
				strBuffer.append(clzPaths.get(i)+";");
			}
		}
		return strBuffer.toString();
	}

}
