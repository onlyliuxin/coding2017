package hw1;

import java.util.Arrays;

public class JavaTest {
	public static void main(String[] args) {
		int[] a = new int[10]; // 创建了一个数组对象
		a[0] = 0;
		a[1] = 1;
		a[2] = 2;
		a[3] = 3;
//		a[10] = 3;
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	// 然后就开始扩张了，ArrayList能自增长
	public static int[] grow(int[]src, int size) {
		return Arrays.copyOf(src, src.length+size);
//		int[] target = new int[src.length+size];
//		System.arraycopy(src, 0, target, 0, src.length);
//		return target;
	}
}
