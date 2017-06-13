package task2.week2.array;

public class ArrayUtil {
	
	public static void main(String[] args){
		ArrayUtil t=new ArrayUtil();
		int[] intArray={1,3,4};
		int[] array1={1,4,5,8};
		int[] array2={3,5,9};
		
		String str=t.join(intArray,"-");
		
		for(int i:t.merge(array1,array2)){
			
			System.out.println(i);
		}
		
		
	}
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		
		int temp=0;
		for(int i=0;i<origin.length/2;i++){
			
			temp=origin[i];
			origin[i]=origin[origin.length-i-1];
			
			origin[origin.length-i-1]=temp;
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
		int[] newArray =new int[oldArray.length];
		int j=0;
		
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				newArray[j]=oldArray[i];
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
		int[] newArray= new int[array1.length+array2.length];
		int size=0;
		
		int i1=0,i2=0;
		while(i1<array1.length&&i2<array2.length){
			if(array1[i1]==array2[i2]){
				newArray[size]=array1[i1];
				i1++;
				i2++;
				size++;
				
			}else if(array1[i1]<array2[i2]){
				
				newArray[size]=array1[i1];
				i1++;
				size++;
				
			}else{
				
				newArray[size]=array2[i2];
				i2++;
				size++;
			}
		}
		
		if(i1<array1.length){
			newArray[size++]=array1[i1++];
			
		}
		if(i2<array2.length){
			newArray[size++]=array2[i2++];
		}
		return  newArray;
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
		
		int[] newArray=new int[oldArray.length+size];
		
		for(int i=0;i<oldArray.length+size;i++){
			if(i<oldArray.length){
				newArray[i]=oldArray[i];
			}
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
		
		int[] array=new int[max];
		
		if(max==1){
			array[0]=max;
		}else if(max==2){
			array[0]=1;
			array[1]=1;
			
		}else if(max>2){
			array[0]=1;
			array[1]=1;
			int i=2;
			
			while(array[i-1]<max){
				
				array[i]=array[i-1]+array[i-2];
				
				i++;
			}
			
			if(array[i-1]>=max){
				array[i-1]=0;
			}
		}
		
		
		return array;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		
		int[] newArray = new int[max];
		if (max > 2) {

			int size = 0, j = 0;
			
			for (int i = 2; i < max; i++) {
				for (j = 2; j < i / 2 + 1; j++) {

					if (i % j == 0) {

						break;

					}

				}

				if (j == i / 2 + 1) {
					newArray[size++] = i;
				}

			}

		}

		return newArray;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] prefectArray=new int[max];
		int k=0;
		
		for(int i=1;i<max;i++){
			
			int sum=0;
			
			for(int j=1;j<i;j++){
				if(i%j==0){
					sum+=j;
				}
			}
			if(i==sum){
				prefectArray[k]=sum;
				k++;
			}
		}
		return prefectArray;
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
				str+= array[i];
				
			}else{
				str+= array[i]+seperator;
			}
		}
		
		return str;
	}
	

}


