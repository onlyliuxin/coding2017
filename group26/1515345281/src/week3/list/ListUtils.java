package week3.list;

public class ListUtils {

	public static void checkIndexRange(int index, int size) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
	}
}
