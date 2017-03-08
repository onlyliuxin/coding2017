package com.coderising.array;

public class ArrayUtilTest {

	public static void main(String[] args) {
		
		ArrayUtil au = new ArrayUtil();
		
		/* test1	reverseArray(int[] origin) */
		int[] arr = {7,9,30,3,88};
		au.reverseArray(arr);
		System.out.println();
		
		/* test2	removeZero(int[] oldArray) */
		int[] arr2 = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		for(int x : au.removeZero(arr2)){
			System.out.print(x + "\t");
		}
		System.out.println();
		
		/* test3	 */
		int[] arr31 = {3,6,9,18,77};
		int[] arr32 = {1,6,7,18,33,56,88};
		for(int x : au.merge(arr31, arr32)){
			System.out.print(x + "\t");
		}
		System.out.println();
		
		/* test4	grow(int [] oldArray,  int size) */
		int[] arr4 = new int[]{2,3,6};
		for(int x : au.grow(arr4, 3)){
			System.out.print(x + "\t");
		}
		System.out.println();
		
		/* test5	 */
		for(int i=0; i<au.fibonacci(13).length; i++){
			System.out.print(au.fibonacci(111)[i] + "\t");
		}
		System.out.println();
		
		/* test6	 */
		for(int i=0; i<au.getPrimes(114).length; i++){
			System.out.print(au.getPrimes(114)[i] + "\t");
			if(i%5 == 0){
				System.out.print("\n");
			}
		}
		System.out.println();
		
		/* test7	getPerfectNumbers(int max) */
		au.getPerfectNumbers(495);
		System.out.println();
		
		/* test8	join(int[] array, String seperator) */
		int[] arr8 = {3,8,9};
		System.out.println(au.join(arr8, "-"));
	}

}
