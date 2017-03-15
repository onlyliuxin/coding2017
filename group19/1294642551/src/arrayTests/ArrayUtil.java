package arrayTests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class ArrayUtil {
	
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public int[] reverseArray(int[] origin){
		
		int len = origin.length;
		int[] arr = new int[len];
		
		for(int i = 0; i < len; i++)
		{
			arr[i] = origin[ len -1 - i];
		}
		
		return arr;
	}
	
	/**
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		int len = oldArray.length;
		for(int i = 0; i < len; i++)
		{
			if(oldArray[i] != 0)
			{
				al.add(oldArray[i]);
			}
		}
		
		int arrLen = al.size();
		int[] arr = new int[arrLen];
		for(int i = 0; i < arrLen; i++)
		{
			arr[i] = al.get(i);
		}
		
		return arr;
		

	}

	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		TreeSet<Integer> tr = new TreeSet<Integer>();
		for(int i = 0; i < array1.length; i++)
		{
			tr.add(array1[i]);
		}
		for(int j = 0; j < array2.length; j++)
		{
			tr.add(array2[j]);
		}
		
		int arrLen = tr.size();
		int[] arr = new int[arrLen];
		int index = 0;
		
		Iterator it = tr.iterator();
		while(it.hasNext())
		{
			arr[index] = (int) it.next();
			index++;
		}
		
		return  arr;
	}
	/**
	 * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size
	 * ע�⣬�������Ԫ��������������Ҫ����
	 * ���� oldArray = [2,3,6] , size = 3,�򷵻ص�������Ϊ
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int [] oldArray,  int size){
		
		int len = oldArray.length;
		int arrLen = len + size;
		int[] arr = new int[arrLen];
		
		for(int i = 0; i < arrLen; i++)
		{
			if (i < len)
				arr[i] = oldArray[i];
			else
				arr[i] = 0;
		}
		
		return arr;
		
	}
	
	/**
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		int first = 1;
		int second = 1;
		int value = 0;
		if(max >= 2)
		{
			al.add(first);
			al.add(second);
		}
		do
		{
			value = first + second;
			if(value < max)
			{
				al.add(value);
				first = second;
				second = value;
			}
		}while(value < max);
		
		int arrLen = al.size();
		int[] arr = new int[arrLen];
		for(int i = 0; i < arrLen; i++)
		{
			arr[i] = al.get(i);
		}
		
		return arr;
		
	}
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		if(max > 2)
			al.add(2);
		
		int value = 3;
		while(value < max)
		{
			int flag = 1;
			for(int i = 2; i < value; i++)
			{
				if(value % i == 0)
				{
					flag = 0;
					break;
				}
			}
			
			if (flag == 1)
				al.add(value);
			
			value++;
		}
		
		int arrLen = al.size();
		int[] arr = new int[arrLen];
		for(int i = 0; i < arrLen; i++)
		{
			arr[i] = al.get(i);
		}
		
		return arr;
		
	}
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i = 1; i < max; i++)
		{
			if (isPerfectNumber(i))
				al.add(i);
		}
		
		int arrLen = al.size();
		int[] arr = new int[arrLen];
		for(int i = 0; i < arrLen; i++)
		{
			arr[i] = al.get(i);
		}
		
		return arr;
	}
	
	public boolean isPerfectNumber(int number)
	{
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for(int i = 1; i < number; i++)
		{
			if(number % i == 0)
				al.add(i);
		}
		
		int value = 0;
		for(int j = 0; j < al.size(); j++)
		{
			value = value + al.get(j);
		}
		
		return value == number;
	}
	
	/**
	 * ��seperator ������ array����������
	 * ����array= [3,8,9], seperator = "-"
	 * �򷵻�ֵΪ"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		
		String str = "";
		int len = array.length;
		for(int i = 0; i < len-1; i++)
		{
			str = str + array[i] + seperator;
		}
		str = str + array[len-1];
		
		return str;
	}
	
	

}
