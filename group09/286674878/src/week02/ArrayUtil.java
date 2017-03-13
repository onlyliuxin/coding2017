package week02;

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
		int temp=0;
		for(int i=1;i<=origin.length/2;i++){
			origin[i]=temp;
			origin[i]=origin[origin.length-(i-1)];
			origin[origin.length-(i-1)]=temp;
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
		int[] re = new int[oldArray.length];
		for(int i=0,j=0;i<oldArray.length;){
			if(oldArray[i]!=0){
				re[j]=oldArray[i];
				j++;
				i++;
			}else i++;
		}
		return re;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int[] me = new int[array1.length+array2.length];
		int k=0;
		 for(int i=0,j=0;i<array1.length&&j<array2.length;){
			if(array1[i]==array2[j]){
				me[k]=array1[i];
				i++;
				j++;
			}else if(array1[i]>array2[j]){
				me[k]=array2[j];
				j++;
			}else{
				me[k]=array1[i];
				i++;
			}
			k++;
		  }
		if(array1.length<array2.length)
		{
			
			for(int m=array1.length+1;m<array2.length;m++,k++)
				me[k]=array2[m];
		}else{
			for(int m=array2.length+1;m<array1.length;m++,k++)
				me[k]=array1[m];
		}
		return  me;
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
	public int[] grow(int[] oldArray,  int size){
		int[] gr = new int[oldArray.length+size];
		for(int i=0;i<oldArray.length;i++){
			gr[i]=oldArray[i];
		}
			
		return gr;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		 int[] fi = new int[max];
		 fi[0]=1;
		 fi[1]=1;
		 for(int i=2;fi[i]<max;i++){
			 fi[i]=fi[i-1]+fi[i-2];
			 
		 }
		return fi;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int nump=0;
		int[] na = new int[max];  //此处不好，有浪费空间，以后改进
		for(int i=1;i<=max;i++){
			if(ifPrime(i))
				na[nump++]=i;
		}
		
		return na;
		
	}
	public boolean ifPrime(int a){
		boolean flag=false;
		for(int i=2;i<=(int)Math.sqrt(a);i++)
			if(a%i == 0)
				break;
			else flag=true;
		
		return flag;
	}
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] np = new int[max];
		int count=0;
		for(int i=1;i<=max;i++){
			if(ifPerfectNum(i))
				np[count++]=i;
		}
		return np;
	}
	//Factor 为求因子函数
	public int[] factor(int a){
		int[] fa = new int[a];
		int count=0;              //count可不可以用length代替，对类中和方法中的field作用范围模糊，需再仔细阅读相关内容
		for(int i=1;i<a;i++){
			if(a%i==0)
				fa[count++]=i;
		}
		return fa;
	}
	//ifPerfectNum函数确认是否为完数
	public boolean ifPerfectNum(int a){
		int[] fa2;
		fa2 = factor(a);
		int sum=0;
		for(int i=0;i<fa2.length;i++){
			sum=fa2[i]+sum;
		}
		if(sum==a)
			return true;
		else return false;
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
		for(int i=0;i<array.length;i++)
			System.out.print(array[i]+seperator);
		return null;
	}
	

}
