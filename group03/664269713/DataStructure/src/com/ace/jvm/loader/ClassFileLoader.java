package com.ace.jvm.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.ace.jvm.clz.ClassFile;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
	    String path = getClassPath() + "//" + className.replaceAll("\\.", "//") + ".class";
	    InputStream in = null;
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    try{
            in = new FileInputStream(path);
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len = in.read(bytes)) != -1){
                out.write(bytes, 0 , len);
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

		return out.toByteArray();
	}
	
	
	public void addClassPath(String path) {
	    clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < clzPaths.size(); i++){
			if(i == clzPaths.size() - 1){
				sb.append(clzPaths.get(i));
			}else{
				sb.append(clzPaths.get(i) + ";");
			}
		}
		return sb.toString();
	}

	public ClassFile loadClass(String className){
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser =  new ClassFileParser();
		return parser.parse(codes);
	}

	

}
