package week02.array;

/**
 * 
 * @author Hui Zhou
 * @version 1.0 2017-02-28
 *
 */

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(origin == null) return;
		
		int mid = origin.length/2;
		for(int i=0;i<mid;i++){
			int sto = origin[i];
			origin[i] = origin[origin.length-i-1];
			origin[origin.length-i-1] = sto;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		if(oldArray == null) return new int[]{};
		
		int zero_sum = 0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i] == 0){
				zero_sum++;
			}
		}
		
		int[] newArray = new int[oldArray.length - zero_sum];
		int j=0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i] != 0){
				newArray[j++] = oldArray[i];
			}
		}
		
		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		if(array1 == null && array2 == null) return new int[]{};
		
		int[] array3 = new int[array1.length + array2.length];
		System.arraycopy(array1, 0, array3, 0, array1.length);
		System.arraycopy(array2, 0, array3, array1.length, array2.length);
		
		//得到重复元素个数
		int counts = 0;
		for(int i=0;i<array3.length-1;i++){
			for(int j=i+1;j<array3.length;j++){
				if(array3[i] == array3[j])
					counts++;
			}
		}
		
		//删除重复元素
		int[] array4 = new int[array3.length - counts];
		int index = 0;
		for(int i=0;i<array3.length;i++){
			boolean flag = false;
			for(int j=0;j<array4.length;j++){
				if(array4[j] == array3[i]){
					flag = true;
					break;
				}
			}
				
			if(flag == false){
				array4[index++] = array3[i];
			}
		}
		
		//冒泡排序
		for(int i=1;i<array4.length;i++){
			for(int j=0;j<array4.length-i;j++){
				if(array4[j]>array4[j+1]){
					int sto = array4[j];
					array4[j] = array4[j+1];
					array4[j+1] = sto;
				}
			}
		}
		return  array4;
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int [] oldArray,  int size){
		if(size<0 || oldArray==null) return null;
		
		int[] newArray = new int[oldArray.length + size];
		for(int i=0;i<oldArray.length;i++){
			newArray[i] = oldArray[i];
		}
		for(int i=0;i<size;i++){
			newArray[oldArray.length+i] = 0;
		}
		return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if(max<=1) return new int[]{};
		else{
			int i=1;
			int j=1;
			int[] fib = new int[max];
			fib[0] = fib[1] = 1;
			int index = 2;
			for(int k=2;k<max;k= i + j){
				i = j;
				j = k;
				fib[index++] = j;
			}
			fib = removeZero(fib);
			return fib;
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max<=2) return new int[]{};
		else{
			int[] prime = new int[max];
			int index =0;
			for(int i=2;i<max;i++){
				int j=2;
				while(i%j!=0){
					j++;
				}
				if(i==j){
					prime[index++] = i;
				}
			}
			prime = removeZero(prime);
			return prime;
		}
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max<=6) return new int[]{};
		else{
			int[] perNum = new int[max];
			int index = 0;
			for(int i=2;i<max;i++){
				int sum = 0;
				for(int j=1;j<i;j++){
					if(i%j==0) sum+=j; 
				}
				if(sum == i) perNum[index++] = i;
			}
			perNum = removeZero(perNum);
			return perNum;
		}
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		String link = "";
		for(int i=0;i<array.length-1;i++){
			link += array[i]+seperator;
		}
		return link+array[array.length-1];
	}
}
