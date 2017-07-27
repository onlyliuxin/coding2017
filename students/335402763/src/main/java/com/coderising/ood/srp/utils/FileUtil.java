package com.coderising.ood.srp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FileUtil {

		public static String[] readFile(File file) throws IOException // @02C
		{
			HashMap map = new HashMap();
			BufferedReader br = null;
			String[] data = null;
			try {
				br = new BufferedReader(new FileReader(file));
				String temp = br.readLine();
				data = temp.split(" ");
			} catch (IOException e) {
				throw new IOException(e.getMessage());
			} finally {
				br.close();
			}
			return data;
		}

}
