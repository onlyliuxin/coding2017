package com.coderising.litestruts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "struts.xml";
		SparseXml sx = new SparseXml(fileName);
		
		File file = new File(fileName);
		InputStream in = new FileInputStream(file);
		sx.parseXml(in);

	}

}
