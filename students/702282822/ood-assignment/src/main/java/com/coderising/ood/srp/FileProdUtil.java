package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class FileProdUtil {
	//if other files, need polymorphically present file reading
	public static void readFile(File file, LinkedList<Theme> themes) throws IOException // @02C
	{	
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String sCurrentLine = "";

			while ((sCurrentLine = br.readLine()) != null) 
			{
				Theme prod = new Product();
				String[] data = sCurrentLine.split(" ");			
				prod.setID(data[0]); 
				prod.setDesc(data[1]); 
				
				System.out.println("产品ID = " + prod.getID() + "\n");
				System.out.println("产品描述 = " + prod.getDesc() + "\n");			
				themes.add(prod);
			}			
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}

}
