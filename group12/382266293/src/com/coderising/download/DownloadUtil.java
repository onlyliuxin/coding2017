package com.coderising.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DownloadUtil {

	private static final int MIN_CONNECTIONS = 3;
	private static final int MAX_CONNECTIONS = 10;

	public static long getCurrentTime() {
		return System.currentTimeMillis();
	}

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

	public static int calculateConnects(int length) {
		int conns = length / 1024 / 1024 / 10;
		if (conns < MIN_CONNECTIONS) {
			return MIN_CONNECTIONS;
		} else if (conns > MAX_CONNECTIONS) {
			return MAX_CONNECTIONS;
		} else {
			return conns;
		}
	}

	public static boolean rename(String from, String to) {
		File file = new File(from);
		if (file.exists()) {
			return file.renameTo(new File(to));
		}
		System.out.println("rename failed");
		return false;
	}

	public static void printDownloadReport(int length, long start, long end) {
		int time = (int) ((end - start) / 1000);
		float speed = (float) length / 1024 / 1024 / time;
		System.out.println("共耗时：" + time + "s，下载速度： " + (float) (Math.round(speed * 100)) / 100 + "Mb/s");
	}

}