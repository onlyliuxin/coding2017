package com.coderising.download.utils;

public class FileDownloadUtil {
	
	public static int[] generateDownloadPosArr(int length){
		int[] posArr = new int[3];
		int firstPos = length/3;
		int secondPos = length/3 * 2;
		
		posArr[0] = 0;
		posArr[1] = firstPos;
		posArr[2] = secondPos;
		
		return posArr;
	}
	public static void main(String[] args) {
		FileDownloadUtil.generateDownloadPosArr(1000);
	}

}
