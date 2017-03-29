package utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
	/**
	 * 将List<Integer>转换为相同顺序和长度的int[]
	 * @param list
	 * @return
	 */
	public static int[] list2Array(List<Integer> list) {
		int size = list.size();
		int[] newArray = new int[size];
		for (int i=0; i<size; i++) {
			newArray[i] = list.get(i);
		}
		return newArray;
	}
	
	/**
	 * 将int[]转换为相同顺序和长度的List<Integer>
	 * @param array
	 * @return
	 */
	public static List<Integer> array2List(int[] array) {
		List<Integer> list = new ArrayList<>();
		for (int e : array) {
			list.add(e);
		}
		return list;
	}
}
