package week2.arrayutil;

import java.util.ArrayList;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(origin==null || origin.length==0){
			return ;
		}
		for(int i=0;i<origin.length/2;i++){
			int temp=origin[i];
			origin[i]=origin[origin.length-1-i];
			origin[origin.length-1-i]=temp;
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
		
		if(oldArray==null || oldArray.length==0){
			return null;
		}
		
		int length=0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i] != 0){
				length++;
			}
		}
		
		int[] result=new int[length];
		int cursor=0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i] != 0){
				result[cursor++]=oldArray[i];
			}
		}
		
		return result;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		
		ArrayList<Integer> list=new ArrayList<Integer>();
		int i=0;
		int j=0;
		while(i<array1.length && j<array2.length){	
			
			if(array1[i] <array2[j]){
				list.add(array1[i]);
				i++;
			}else if(array1[i] > array2[j]){
				list.add(array2[j]);
				j++;
			}else{
				list.add(array1[i]);
				i++;
				j++;
			}
		}
		while(i<array1.length){
			list.add(array1[i++]);
		}
		while(j<array2.length){
			list.add(array2[j++]);
		}
	
		int[] result=new int[list.size()];
		for(int k=0;k<list.size();k++){
			result[k]=list.get(k);
		}
		return  result;
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
	    if(size<0){
	    	throw new IndexOutOfBoundsException();
	    }
		
		int[] newArray=new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
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
		if(max<0){
			throw new IndexOutOfBoundsException();
		}
		if(max==1){
			return new int[0];
		}
		if(max==2){
			return new int[]{1};
		}
		
		int[] result=new int[5];//开数组5，便于扩容测试
		result[0]=1;
		result[1]=1;
		int cursor=2;		
		while(result[cursor] < max){//dp
		    if((cursor+1) >=result.length ){
		    	result=this.grow(result, 5);
		    }
		    result[cursor]=result[cursor-2]+result[cursor-1];
		    cursor++;
		}
		return result;
	}

	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max<=1){
			return new int[0];
		}
		if(max == 2){
			return new int[]{2};
		}
		
		int[] temp=new int[max];
		temp[2]=2;
		int primeNum=0;
		for(int i=3;i<max;i+=2){
			temp[i]=i;
			primeNum++;
			for(int j=i;j<max;j+=j){//数筛法
				temp[j]=0;
				primeNum--;
			}
		}
		
		int[] result=new int[primeNum];
		int cursor=0;
		for(int i=2;i<max;i++){
			if(temp[i]==i){
				result[cursor++]=i;
			}
		}
		return result;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max < 6){
			return new int[0];
		}
		
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=6;i<max;i++){
			int sum=1;
			for(int j=2;j<i/2;j++){
				if(i % j == 0){
					sum+=(j+i/j);
				}
			}
			if(sum == i){
				list.add(i);
			}
		}
		int[] result=new int[list.size()];
		for(int i=0;i<result.length;i++){
			result[i]=list.get(i);
		}
		return result;
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
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<array.length;i++){
			sb.append(array[i]);
			if(i!=array.length-1){
				sb.append(seperator);
			}
		}
		return sb.toString();
	}
}
