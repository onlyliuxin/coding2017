
package com.coderising.ood.srp;
/** 
 * @author  作者 Denny
 * @date 创建时间：Jun 25, 2017 10:27:58 AM 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileUtil {

	/**
	 * 根据文件路径获取文件,如果文件不存在,抛出异常
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File readFile(String fileName) throws FileNotFoundException{
		File file = new File(fileName);
		if(!file.exists()){
			throw new FileNotFoundException();
		}
		return file;
	}

	public static List<String[]> parseToString(File file, String regex) {
		List<String[]> list = new ArrayList<String[]>();
		BufferedReader bf= null;
		try {
			if(file != null && file.exists( )){
				bf = new BufferedReader(new FileReader(file));
				String temp = null;
				while ((temp = bf.readLine()) != null) {
					String[] strs = temp.split(regex);
					list.add(strs);
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(bf != null){
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}
