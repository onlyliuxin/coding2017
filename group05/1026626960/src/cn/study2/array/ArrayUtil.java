package cn.study2.array;

public class ArrayUtil {
	
	/**
	 *  给定一个整形数组a , 对该数组的值进行置换
	 *	例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
	 *	如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
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
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
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
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 */
	public int[] reshapeArr(int a1[], int a2[]){
		int size = a1.length+a2.length;
		int a3[] = new int[size];
		//未完成
		return a3;
	}
	
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
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
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
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
	 * 求给定数的斐波拉契数
	 */
	//未完成
	
	
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
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
	 * 判断是否为素数
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
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
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
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
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
