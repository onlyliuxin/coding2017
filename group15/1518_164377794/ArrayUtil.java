package ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		//创建一个容器
		int[] arr = origin;
		//置换数组
		for(int i=0;i<origin.length;i++){
			origin[origin.length-1-i]=arr[i];
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
		List list = new ArrayList();
		//将数组为0的值过滤，使用list来确定数组长度
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i] != 0){
				list.add(oldArray[i]);
			}
		}
		int[] arr = new int[list.size()];
		//判断list是否有值
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				arr[i] = (int)list.get(i);
			}
		}
		return arr;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int a=0,b=0;
		int[] arr=new int[array1.length+array2.length];
		for(int i=0;i<array1.length+array2.length;i++){
			if(a>=array1.length&&b<array2.length){
				arr[i]=array2[b];
				b++;
			}else if(b>=array2.length&&a<array1.length){
				arr[i]=array1[a];
				a++;
			}else if(array1[a]<array2[b]){
				arr[i]=array1[a];
				a++;
			}else{
				arr[i]=array2[b];
				b++;
			}
		}
		return  arr;
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 * @throws Exception 
	 */
	public int[] grow(int [] oldArray,  int size){
		//当输入负数出现下标越位
		if(size<0){
			throw new ArrayIndexOutOfBoundsException("存在标越界风险");
		}
		//扩容
		oldArray = Arrays.copyOf(oldArray, oldArray.length+size);
		return oldArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if(max<2){
			return new int[0];
		}
		int a=1,b=0,c=0;
		List list = new ArrayList();
		//获取符合要求的斐波那契数列
		for(int i=0;i<max;i++){
			if(a<max){
				list.add(a);
				a=b+a;
				b=(int)list.get(i);
			}else{
				i=max;
			}
		}
		int[] arr = new int[list.size()];
		//将list值转入数组
		for(int i=0;i<list.size();i++){
			arr[i] = (int)list.get(i);
		}
		return arr;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max<2){
			return new int[0];
		}
		List list = new ArrayList();
		for (int n = 2; n <= max; n++) {
            // 判断n是否是质数
            int m = (int) Math.sqrt(n);
            int i = 2;
            for (; i <= m; i++) {
                if (n % i == 0)break;
			}
            if(i>m)list.add(n);
		}
		int[] arr = new int[list.size()];
		for(int i=0;i<arr.length;i++){
			arr[i] = (int)list.get(i);
		}
		return arr;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int sum=0;
		List list = new ArrayList();
		for(int i=2;i<max;i++){
			sum=0;
			for(int j=1;j<i;j++){
				if(i%j==0){
					sum+=j;
				}
			}
			//判断i否是完数
			if(sum==i){
				list.add(sum);
			}
		}
		int[] arr = new int[list.size()];
		for(int i=0;i<list.size();i++){
			arr[i]=(int)list.get(i);
		}
		return arr;
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
		String str="";
		for(int i=0;i<array.length;i++){
			if(i==array.length-1){
				str+=array[i];
			}else{
				str+=array[i]+seperator;
			}
		}
		return str;
	}
}
