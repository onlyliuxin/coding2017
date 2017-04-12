package day_2017_2_26_SecondHomework;

import java.util.Arrays;

import javax.management.RuntimeErrorException;

public class ArrayUtil {
	
	/* *
	 * 给定一个整形数组 a ，对该数组的值进行置换
	 * 例如： a = [7, 9, 30, 3], 置换后为 [3, 30, 9, 7]
	 * */

	/*public ArrayUtil(int[] a2) {
		this.a = a2;
	}*/
	public void reverseArray(int [] a){
		if(null == a){
			System.out.println("空指针----");
			return;
		}
		int temp;
		int last = a.length-1;
		for (int i = 0; i < a.length/2; i++) {
			temp = a[i];
			a[i] = a[last];
			a[last--] = temp;
		}
	}
	public void print(int [] a){
		if(null == a){
			System.out.println("空指针----");
			return;
		}
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	/* *
	 * 现在有如下的一个数组， int oldArr[] = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中的值为 0 的项去掉，将不为 0 的值存入一个新的数组，生成的新数组为：
	 * {1,3,4,5,6,6,5,4,7,6,7,5}
	 * @param oldArray
	 * @return
	 */
	 public int [] removeZero(int [] oldArray){
		 if(null == oldArray){
			 return null;
		 }
		 int count = 0;
		 int oldArrayLength = oldArray.length;
		 for(int i = 0; i < oldArrayLength;){
			 if(oldArray[i]==0){
				 for(int j = i; j < oldArrayLength -1; j++){
					 oldArray[j] = oldArray[j+1];
				 }
				 oldArrayLength--;
				 count++;
			 }else{
				 i++;
			 }
		 }
		 int [] target = new int[oldArray.length-count];
		 System.arraycopy(oldArray, 0, target, 0, oldArray.length-count);
		 return target;
	 }
	

	
	
	
	
	
	
	
	
	
	
	
	
}
