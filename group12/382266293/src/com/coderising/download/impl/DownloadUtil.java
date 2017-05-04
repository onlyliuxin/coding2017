package com.coderising.download.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadUtil {

	public static void createTempFile(String tempName, int len) {
		File file = new File(tempName);
		if (file.exists()) {
			System.out.println("tempfile already created");
			return;
		}
		FileOutputStream temp = null;
		try {
			temp = new FileOutputStream(tempName);
			int length = len;
			byte[] buffer = new byte[1024];
			long times = length / 1024;
			int left = length % 1024;
			for (int i = 0; i < times; i++) {
				temp.write(buffer);
			}
			temp.write(buffer, 0, left);
			System.out.println("tempFile " + tempName + " created");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				temp.flush();
				temp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static long getCurrentTime() {
		return System.currentTimeMillis();
	}

}
