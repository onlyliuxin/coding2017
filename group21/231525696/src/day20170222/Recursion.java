package day20170222;

public class Recursion {
	
	public static void main(String[] args) {
		System.out.println(getResult(5,1));
		System.out.println(getResult2(5));
	}
	
	//尾递归，每次调用自己时没有表达式（需要等待下次调用的返回值来计算），而是一个确切的数值，运行时，jvm会一直使用一个栈桢
	public static int getResult(int n, int result){
		if(n == 1){
			return result;
		} else {
			return getResult(n-1, n*result);
		}
	}
	
	//普通递归，调用自己时传递的是个表达式，需要等待下次调用的返回值来进行计算，会造成一定要递归进最深层获得确切返回值，
	//再返回上一层计算上一层，依次类推，到最上层计算出最后结果。
	//每调用一次带表达式的递归，内存中会有一个指向的栈桢，递归链太长，栈桢过大，内存溢出
	public static int getResult2 (int n){
		if (n == 1) {
			return 1;
		} else {
			return n * getResult2 (n-1);
		}
	}
}
