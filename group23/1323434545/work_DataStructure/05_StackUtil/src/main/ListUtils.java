package main;

public class ListUtils {
	public static boolean CheckIndexInRange(int start, int end, int index) {
		if (index >= start && index <= end) {
			return true;
		}
		throw new IndexOutOfBoundsException();
	}

}
