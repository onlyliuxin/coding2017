package edu.coerscnu.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtil类负责读取.txt文件内容，并以List<String[]>（产品id，产品描述）形式返回。
 * 
 * @author xujie
 *
 */
public class FileUtil {

	public static List<String[]> readFile(String path) throws IOException {
		BufferedReader br = null;
		List<String[]> list = new ArrayList<>();
		try {
			File file = new File(path);
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			String temp;
			while ((temp = br.readLine()) != null) {
				String[] data = temp.split(" ");
				list.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return list;
	}
}
