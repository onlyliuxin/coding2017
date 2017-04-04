package me.lzb.loader;

import me.lzb.utils.FileUtils;
import me.lzb.utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<>();

	public byte[] readBinaryCode(String className) throws IOException {
        String fileName = className.replaceAll(".*\\.", "")  + ".class";
        String pkg = className.replaceAll("\\.[^\\.]+$", "");
        String packagePath = pkg.replaceAll("\\.", "\\\\");


        String path = "";
        for (String s : clzPaths) {
            if (FileUtils.isFileExist(s + packagePath, fileName)){
                path = s;
                break;
            }
        }

        if(StringUtils.isBlank(path)){
            throw new IOException("class file not found");
        }

		return FileUtils.readByteCodes(path + packagePath + "\\" + fileName);

	}


	public void addClassPath(String path) {
        clzPaths.add(path);
	}



	public String getClassPath(){
        StringBuilder buffer = new StringBuilder();
        for (Iterator<String> iterator = clzPaths.iterator(); iterator.hasNext();) {
            buffer.append(iterator.next() + (iterator.hasNext() ? ";" : ""));
        }
        return buffer.toString();
	}




}
