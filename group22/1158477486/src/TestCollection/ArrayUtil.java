package TestCollection;
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
		 
			for(int i=0,j=origin.length-1;i<j;i++,j--){
				int r=origin[i];
				origin[i]=origin[j];
				origin[j]=r;
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
		int size=0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				 size++;
			} 
		}
		int  []arr=new int[size];
		
		int j=0;
		 
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				arr[j++]=oldArray[i]; 
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
		int m=0;
		 for(int i=0;i<array1.length;i++){
			 for(int j=0;j<array2.length-m;j++){
				 if(array1[i]==array2[j]){
					 array2[j]=array2[array2.length-m-1];
					 m++;
				 }
			 }
		 }
		 int length=array1.length+array2.length-m;
		 System.out.println(length);
		 int array[]=new int [length];
		 int x=0;
		 for(int i=0;i<array1.length;i++){
		array[x++]=array1[i];	 
		 }
		 for(int j=0;j<array2.length-m;j++){
			 array[x+j]=array2[j];
		 }
		 int swap=0;
		 for( int i = 0; i < array.length; ++i )
			{
				for(int j = 1; j < array.length-i; ++j )
				{
					if( array[j-1] > array[j] )		// 如果顺序错了，就交换一下
					{
						swap = array[j];
						array[j] = array[j-1];
						array[j-1] = swap;
					}
				}
			}
		 
		return  array;
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
		int newArray[]=new int [oldArray.length+size];
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
		 int[] a=new int [100];
		 a[0]=1;
		 a[1]=1;
		 int i =2;
		 for(;i<a.length;i++){
			 if(a[i-1]<max&&a[i-1]!=0){//要多设置一个条件保证i等于实际坐标加1
			 a[i]=a[i-1]+a[i-2];
			 }
			 else{
				 break;
			 }
			} 
		 int[] b = new int[i-1];//把 a中的值给b,i-1是为了把多存的那个元素删除
		 for (int j = 0; j <i-1; j++) {
			 b[j] = a[j];
		 }
		 return b;
		 
		 
		 
//		 for(int i=0;i<a.length;i++){
//			 if(a[i]<max&&max<a[i+1]){
//				 int arr[]=new int[i+1];
//				 for(int j=0;j<=i;j++){
//					 arr[j]=a[j];
//				 }
//				 return arr;
//			 }
//		 }
		 
		 
		 
		 
		 
/*		int index = 0;
		for (int i = 0; i < a.length; i++) {
			if(a[i]<max){
				index++;
			}else{
				int [] arr = new int[index];
				
				for (int j = 0; j < index; j++) {
					arr[j] = a[j];
					
				}
				return arr;
				
			}
		}*/
		 
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		ArrayList<Integer>list=new ArrayList<Integer>();
		   for (int i = 2; i <max; i++) {  
	            list.add(i);  
	        }  
	        for (int j = 0; j < list.size() && list.get(j) * list.get(j) <max; j++) {  
	            for (int k = 0; k < list.size(); k++) {  
	                if ((list.get(k) != list.get(j))  
	                        && list.get(k) % list.get(j) == 0) {  
	                    list.remove(k);  
	                }  
	            }  
	        }  
		 
		Object[]arr= list.toArray( );
		int[]arr1=new int[arr.length];
		for(int i=0;i<arr.length;i++){
			arr1[i]=(int) arr[i];
		 
		}
		
		return arr1;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		ArrayList<Integer>list1=new ArrayList<Integer>();
		ArrayList<Integer>list=new ArrayList<Integer>();
		for(int i=1;i<max;i++){
			list .removeAll(list);
			for(int j=1;j<=Math.sqrt(i);j++){
				
				if(i%j==0){
					 list.add(j);
					 list.add(i/j);
					}
			}
			 int count=0;
			  for(int k=0;i<list.size();k++){
				 
				count+=list.get(k); 
			  }
			  if(count==i){
				list1.add(i); 
				System.out.print("ssssssss:"+i);
			  }	
		}
		Object[]arr= list1.toArray( );
		int[]arr1=new int[arr.length];
		for(int i=0;i<arr.length;i++){
			arr1[i]=(int) arr[i];
		 
		}
		
		return arr1;
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
		String newString=array[0]+"";
		for(int i=1;i<array.length;i++){
			newString=newString+seperator+array[i];
		}
		return newString;
	}
	

}
