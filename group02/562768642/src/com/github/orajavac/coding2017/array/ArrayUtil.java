package com.github.orajavac.coding2017.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int temp;
		int c=origin.length;
		for (int i=0,j=origin.length-1;i<c/2;i++,j--){
			temp=origin[i];
			origin[i]=origin[j];
			origin[j]=temp;
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
		int new_length=0;
		for (int i=0;i<oldArray.length;i++){
			if (oldArray[i]==0){	//5
				for (int j=i;j<oldArray.length;j++){//j=4
					if (oldArray[j]!=0){
						oldArray[i]=oldArray[j];
						oldArray[j]=0;
						new_length++;
						break;
					}
				}
			}else{
				new_length++;
			}
		}
		return grow(oldArray,new_length);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int[] target = new int[array1.length+array2.length];
		int i=0;
		int j=0;
		int k=0;
		int t=0;
		boolean b=false;

		for (i=0;i<array1.length;i++){
			target[i]=array1[i];
		}
		/*System.out.println(i);*/
		for (j=0;j<array2.length;j++){
			for (k=0;k<target.length;k++){
				if (array2[j]==target[k]){
					b=true;
					break;
				}
			}
			if(!b){
				target[i]=array2[j];
				i++;
			}
			b=false;
		}
		
		for (i=0;i<target.length;i++)
			for(j=0;j<target.length-i-1;j++){
				if(target[j]>target[j+1]){
					t=target[j];
					target[j]=target[j+1];
					target[j+1]=t;
				}
			}		
		return removeZero(target);
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
		int[] target = new int[size];
		for (int i=0;i<target.length;i++){
			target[i]=oldArray[i];
		}
		return target;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if(max==1)
			return new int[1];
		int s1=0;
		int s2=1;
		int c=0;
		int length=1;
		int j=0;
		for (int i=0;i<max;i++){
			c=s1+s2;
			s1=s2;
			s2=c;
			if (c<max){
				//System.out.print(c+" ");
				length++;
			}else{
				break;
			}
		}
		//System.out.println();
		s1=0;s2=1;c=0;
		int[] p = new int[length];
		p[0]=1;
		for (int i=0;i<max;i++){
			c=s1+s2;
			s1=s2;
			s2=c;
			if (c<max){
				j++;
				p[j]=c;
			}else{
				break;
			}
		}
		return p;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int length=0;
		int j=0;
		for (int i = 2; i < max; i++) {
			for (int k = 2; k <= i; k++) {
				if (i % k == 0 && i != k) {
						break;
				}
				if (i % k == 0 && i == k) {
					length++;
				}
			}
		}
		int[] p = new int[length];
		for (int i = 2; i < max; i++) {
			for (int k = 2; k <= i; k++) {
				if (i % k == 0 && i != k) {
						break;
				}
				if (i % k == 0 && i == k) {
					p[j]=i;
					j++;
				}
			}
		}
		return p;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int length=0;
		int j=0;
		for (int i=1;i<=max;i++){
			if(i!=max&&max%i==0){
				length++;
			}
		}
		int[] p = new int[length];		
		for (int i=1;i<=max;i++){
			if(i!=max&&max%i==0){
				p[j]=i;
				j++;
			}
		}
		return p;
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
		String join = "";
		for (int i=0;i<array.length;i++){
			if (i!=array.length-1){
				join+=array[i]+seperator;
			}else{
				join+=array[i];
			}
		}
		return join;
	}
	

}
