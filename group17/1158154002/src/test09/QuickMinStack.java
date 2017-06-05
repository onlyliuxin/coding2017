package test09;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	private int[] arr=new int[10];
	private int head=-1;
	private int position=0;
	
	public void push(int data){
		arr[++head]=data;
		if (data<arr[position]) {
			position=head;
		}
	}
	
	public int pop(){
		int result=arr[head];
		if (head==position) {
			position=0;
			for (int i = 0; i <head; i++) {
				if (arr[i]<arr[position]) {
					position=i;
				}
			}
		}
		arr[head]=0;
		head--;
		return result;
	}
	
	public int findMin(){
		return arr[position];
	}
}