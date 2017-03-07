package cn.study2.array;

public class ArrayUtil {
	
	/**
	 *  ����һ����������a , �Ը������ֵ�����û�
	 *	���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
	 *	���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 */
	public void exchangeArray(int[] a){
		int index = a.length;
		int a1 = 0;
		for(int i = 1; i < index/2; i++){
			a[i] = a1;
			a[i] = a[index - i];
			a[index - i] = a[i];
		}
	}
	
	/**
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 */
	public int[] removeZero(){
		int oldArr[] = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int size = 0;
		for(int i = 0; i < oldArr.length; i++){
			if(oldArr[i] != 0){
				size++;
			}
		}
		int newArr[] = new int[size];
		for(int i = 0; i < oldArr.length; i++){
			int j = 0;
			if(oldArr[i] != 0){
				newArr[j] = oldArr[i];
				j++;
			}
		}
		return newArr;
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 */
	public int[] reshapeArr(int a1[], int a2[]){
		int size = a1.length+a2.length;
		int a3[] = new int[size];
		//δ���
		return a3;
	}
	
	/**
	 * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size
	 * ע�⣬�������Ԫ��������������Ҫ����
	 * ���� oldArray = [2,3,6] , size = 3,�򷵻ص�������Ϊ
	 * [2,3,6,0,0,0]
	 */
	public int[] addSize(int oldArr[],int size){
		int newSize = oldArr.length+size;
		int newArr[] = new int[newSize];
		for(int i = 0; i < newArr.length; i++){
			if(i < oldArr.length){
				newArr[i] = oldArr[i];
			}
			newArr[i] = 0;
		}
		return newArr;
	}
	
	/**
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
	 */
	public int[] method1(int max){
		int Arr[] = new int[max];
		if(max <= 1){
			return null;
		}else if(max <= 2){ 
			int a[] = {1,1,2};
            return a; 
        }else{
        	Arr[0] = 1;
        	Arr[1] = 1;
        	Arr[2] = 2;
        	int n = 3;
        	while(n < max){
        		Arr[n] = Arr[n-2] + Arr[n-1];
        	}
        	return Arr;
        }
	}
	
	/**
	 * ���������쳲�������
	 */
	//δ���
	
	
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 */
	public int[] getPrimes(int max) {
		int size = 0;
		for (int i = 0; i < max; i++) {
			if (isPrimes(i)) {
				size++;
			}
		}
		int Arr[] = new int[size];
		int j = 0;
		for (int i = 0; i < max; i++) {
			if (isPrimes(i)) {
				Arr[j++] = i;
			}
		}
		return Arr;
	}
	
	/**
	 * �ж��Ƿ�Ϊ����
	 */
	public boolean isPrimes(int num){
		if(num<=1){
			return false;
		}else{
			for(int i=2;i<num/2;i++){
				if(num%i==0){
					return true;
				}
			}
		}
		return false;
	}
		
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 */
	public int[] isWanshu(int max){
		if(max<=0){
			return null;
		}
		int size = 0;
		for(int i = 0; i < max; i++){
			if(max/i==0){
				size++;
			}
		}
		int []a = new int[size];
		int j = 0;
		for(int i = 0; i < max; i++){
			if(max/i==0){
				a[j++]=i;
			}
		}
		return a;
	}
	
	
	/**
	 * ��seperator ������ array����������
	 * ����array= [3,8,9], seperator = "-"
	 * �򷵻�ֵΪ"3-8-9"
	 */
	public String join(int array[],String seperator){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < array.length; i++){
			if(i<array.length){
				sb.append(array[i]+"-");
			}else{
				sb.append(array[i]+"");
			}
		}
		return sb.toString();
	}
		
		
		
	
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
