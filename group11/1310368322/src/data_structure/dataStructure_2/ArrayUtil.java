package day_2017_2_26_SecondHomework;

import java.util.Arrays;

import javax.management.RuntimeErrorException;

public class ArrayUtil {
	
	/* *
	 * ����һ���������� a ���Ը������ֵ�����û�
	 * ���磺 a = [7, 9, 30, 3], �û���Ϊ [3, 30, 9, 7]
	 * */

	/*public ArrayUtil(int[] a2) {
		this.a = a2;
	}*/
	public void reverseArray(int [] a){
		if(null == a){
			System.out.println("��ָ��----");
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
			System.out.println("��ָ��----");
			return;
		}
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	/* *
	 * ���������µ�һ�����飬 int oldArr[] = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * Ҫ�����������е�ֵΪ 0 ����ȥ��������Ϊ 0 ��ֵ����һ���µ����飬���ɵ�������Ϊ��
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
