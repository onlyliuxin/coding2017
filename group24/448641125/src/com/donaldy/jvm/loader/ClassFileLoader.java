package com.donaldy.jvm.loader;

import java.io.*;
import java.util.ArrayList;

import com.donaldy.jvm.clz.ClassFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	static final int BUFFER_SIZE = 1024;

	public byte[] readBinaryCode(String className) {
		className = className.replace(".", File.separator) + ".class";

		for (String path : this.clzPaths) {

			String clzFileName = path + File.separator + className;
			byte [] codes = loadClassFile(clzFileName);

			if (codes != null) {
				return codes;
			}
		}

		return null;
	}

	private byte[] loadClassFile(String clzFileName) {
		File f = new File(clzFileName);

		try {

			return IOUtils.toByteArray(new FileInputStream(f));

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	public void addClassPath(String path) {
		if (this.clzPaths.contains(path))
			return;

		this.clzPaths.add(path);
	}

	public String getClassPath() {
		return StringUtils.join(this.clzPaths, ";");
	}

	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);

	}








	////////////////////////////////Backup///////////////////////////////
	public byte[] readBinaryCode_V1(String className) {

		for (String clzPath : clzPaths) {

			File file = new File(clzPath + className.replace(".", "\\") + ".class");


			if (!file.exists())
				continue;

			try (
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
				ByteArrayOutputStream bos = new ByteArrayOutputStream()
			)
			{

				byte [] buffer = new byte[BUFFER_SIZE];

				int len;

				while ((len = bis.read(buffer, 0, BUFFER_SIZE)) > 0) {
					bos.write(buffer, 0, len);
				}

				return bos.toByteArray();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
		
	}
	
	public String getClassPath_V1(){
		StringBuilder sb = new StringBuilder();

		int length = this.clzPaths.size();

		for (int i = 0 ; i < length; ++i) {
			sb.append(this.clzPaths.get(i));
			if (i + 1 < length)
				sb.append(";");
		}

		return sb.toString();
	}

	

	

}
