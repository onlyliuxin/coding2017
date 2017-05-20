package jvm;

import jvm.classfile.ClassFile;
import jvm.classfile.ClassParser;
import jvm.exception.ClassDuplicateException;
import jvm.exception.ClassNotExistsException;
import jvm.exception.ReadClassException;
import jvm.util.ArrayUtils;
import jvm.util.ByteUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassFileLoader {

	private List<String> classPaths = new ArrayList<>();
	
	public byte[] readBinaryCode(String className) throws ReadClassException {
		File file = getClassFile(className);
		if (file == null) {
			throw new ClassNotExistsException();
		}
		InputStream is;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new ClassNotExistsException();
		}
		List<Byte> bytes = new ArrayList<>();
        byte[] buf = new byte[1024];
        int len;
		try {
			while ((len = is.read(buf)) != -1) {
                bytes.addAll(ArrayUtils.toList(buf, 0, len));
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ArrayUtils.toArray(bytes);
	}

	private File getClassFile(String className) throws ClassDuplicateException {
		int split = className.lastIndexOf('.');
		if (split == -1) {
			split = className.lastIndexOf('/');
		}
		String fileName = className.substring(split + 1) + ".class";

		List<File> files = new ArrayList<>();
		for (String path : classPaths) {
			files.addAll(getFiles(new File(path), fileName));
		}
		if (files.size() > 1) {
			throw new ClassDuplicateException();
		}
		return files.size() == 1 ? files.get(0) : null;
	}

	private List<File> getFiles(File path, String fileName) {
		List<File> files = new ArrayList<>();
		File[] listFile = path.listFiles();
		if (listFile == null) {
			return files;
		}

		for (File f : listFile) {
			if (f.isDirectory()) {
				files.addAll(getFiles(f, fileName));
			} else if (f.getName().equals(fileName)) {
				files.add(f);
			}
		}
		return files;
	}

	public void addClassPath(String path) {
		if (path != null && !"".equals(path)) {
			classPaths.add(path);
		}
	}

	public String getClassPath() {
		StringBuilder builder = new StringBuilder();
		classPaths.forEach((i) -> builder.append(';').append(i));
		return builder.substring(1);
	}

	boolean checkMagicNumber(byte[] bytes) {
		String magicNumber = "cafebabe";
		String str = ByteUtils.toHexString(bytes, 0, 4);
		return magicNumber.equals(str.toLowerCase());
	}

	public ClassFile load(String className) throws ReadClassException {
		byte[] bytes = readBinaryCode(className);
		if (checkMagicNumber(bytes)) {
			return ClassParser.parse(bytes);
		}
		return null;
	}
}
