package day20170219;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;

public class JavaTest {
	public static void main(String[] args) {
		int[] a = new int[10];
		a[0] = 0;
		a[1] = 1;
		a[2] = 2;
		a[9] = 9;
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
	}
	
	public static int[] grow(int[] src, int size){
		
		//return Arrays.copyOf(src, src.length + size);
		List list = new LinkedList();
		Stack stack = new Stack();
		LinkedList ls = new LinkedList();
		int[] target = new int[src.length + size];
		System.arraycopy(src, 0, target, 0, src.length);
		
		return target;
	}
}
