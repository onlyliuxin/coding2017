package com.eulerlcs.jmr.xmlparser.digester.util;

import java.io.File;

public class FileUtils {
	public static File getXmlFile(String fileName) {
		String userDir = System.getProperty("user.dir");
		String fullName = userDir + File.separator + "data" + File.separator + fileName + ".xml";
		File xml = new File(fullName);

		return xml;
	}
}
