package cn.task2;

public class ArrayUtilTest {

	public static void main(String[] args) {
		
		ArrayUtil au = new ArrayUtil();
/*		
		int[] a = {1,5,47,9,3,4,};
		System.out.println("转换前a：2-->"+a);
		for(int i=0;i<a.length;i++){
			System.out.print("a["+i+"] = "+a[i]+"  ");
		}
		System.out.println("----------");
		au.reverseArray(a);
		System.out.println("转换后a：2-->"+a);
		System.out.println();
		for(int i=0;i<a.length;i++){
			System.out.print("a["+i+"] = "+a[i]+"  ");
		}*/
		
/*		int[] a = {0};
		int[] b =au.removeZero(a);
		
		for(int i=0;i<b.length;i++){
			System.out.println("b["+i+"] = "+b[i]);
		}*/
		
		int[] a = {1,5,47,9,3,4,};
		int[] b = {3,5};
		
		//au.grow(a, 3);
		
		//int c[] = au.fibonacci(16);
		
		int[] c = au.getPrimes(23);
		for(int i=0;i<c.length;i++){
			System.out.print("c["+i+"] = "+c[i]+"  ");
		}
		
		System.out.println(au.join(a, "-"));
	}
}
