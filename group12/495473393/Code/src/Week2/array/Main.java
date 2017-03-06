package Week2.array;

public class Main {

	public static void main(String[] args) {
		ArrayUtil arrayUtil = new ArrayUtil();
		int[] a = { 1,3,5,7};
		int[] b={ 2,4,6,8,};
		int[] x = arrayUtil.merge(a,b);
		for (int i : x) {
			System.out.println(i);
		}
	}

}
