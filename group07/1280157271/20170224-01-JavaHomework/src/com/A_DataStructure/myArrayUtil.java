package com.A_DataStructure;
 

public class myArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int[] temp = new int[origin.length];
		int i=0,j=0;
		int originLegth=origin.length-1;
		int originLegthTemp=originLegth; 
		for( i=0;i<origin.length;i++){
			temp[i] = origin[i];
		}
		//1、把数组后面置换到前面
		for( i=0;i<origin.length/2 ;i++){ 
			origin[i] = origin[originLegth--];
		} 
		//2、把数组前面置换到后面 
		for( i=0;i<origin.length/2 ;i++){ 
			origin[originLegthTemp--] = temp[i];
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
		int[] newArray= new int[oldArray.length];
		int j=0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i] != 0){ 
				 newArray[j++]=oldArray[i];
			}
		} 
		//去尾部0操作
		int[] newArray2 = new int[j];//j++,所以j此时会和数组大小一样 
		for(int i=0;i<j;i++){
			newArray2[i]=newArray[i];
		}
		return newArray2;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int a1Length = array1.length;
		int a2Length = array2.length;
		int newLength = a1Length+a2Length; //新数组长度 
		int[] newArr = new int[newLength];
		
		//1、先把a1直接复制进去
		for(int i=0;i<a1Length;i++){ 
			newArr[i] = array1[i];
		}
		//2、将a2中不重复的值复制进去
		int newArrSize = a1Length;
		for(int j=0;j<a2Length;j++){
			int mark=1;  //标志有重复元素 
			for(int i=0;i<a1Length;i++){ 
				if(array2[j] == array1[i]){
					 mark = 0;
				 
				}
			}//内for循环一次比较了一次（array2中的一个元素与array1当中的所有元素是否有相同）
			if(mark == 1){//array2中的低j-1个元素没有重复，放入newArray中
				newArr[newArrSize++] = array2[j];//newArr中已经存在了array1
			}
		}
	 
		newArr = this.removeZero(newArr);
		//4、冒泡排序
		int temp;
		for(int i=0;i<newArr.length-1;i++){//n个数，一趟找一个最大数，需要n-1趟（最后一个数不需要排）
			for(int j=1;j<newArr.length-i;j++){//每一趟两个比较，只需比较没有比较的数
				if(newArr[j-1]>newArr[j]){//如果前者比后者大，就交换位置
					temp = newArr[j-1];
					newArr[j-1] = newArr[j];
					newArr[j] = temp;
				}
			}
		}
		
		return  newArr;
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
		int[] newArray = new int[oldArray.length+size];
		//System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		for(int i=0;i<oldArray.length;i++){
			newArray[i] = oldArray[i];
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
		int[] fibonacci = new int[max+1];//规律看出，数列的长度增长赶不上max值的增长
		if(max<=1){
			System.out.println("参数错误！！");
			return null;
		}else{
			int temp;
			fibonacci[0]=1;//不管max为几，[0]都为1(max取值[1-无穷]
			int size=1;  //fibonacci数组中已使用的空间
			if(max>1){ //2或以上
				fibonacci[1]=1; 
				for(int i=2;i<max;i++){//初始时fibonacci[i]=1<max=2的
					temp = fibonacci[i-1]+fibonacci[i-2]; 
					if(temp>=max){
						break;//跳出循环
					}
					fibonacci[i] = temp;//超过max的话就跳出去了，不会存入数组
				}
			}
		}
		return this.removeZero(fibonacci);//去0操作
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] newPrims = new int[max];
		int size=0;
		
		if(max<2){
			return null;
		}else{
			 for(int i=2;i<max;i++){//要判断的数
				int mark = 1; //素数标记，
				for(int j=2;j<i;j++){//小于被除数的所有除数
					if(i%j ==0){
						mark = 0;
						break;
					}
				}
				if(mark == 1){
					newPrims[size++] = i;
				}
			 }
		}
		return this.removeZero(newPrims);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] perfectNumber = new int[max];  
		int pNumSize=0;
		
		for(int i=1;i<max;i++){
			int sum=0; 
			for(int j=1;j<i;j++){//除数 
				if(i%j==0){//说明j是i的一个因数
					 sum +=j;
					
				}
			}//内循环结束一次，找出了i的所有因子 
			if(sum == i){//和等于i的除数之和
				 perfectNumber[pNumSize++]=i;
			}
		}
		
		return this.removeZero(perfectNumber);
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
		String s="";
		for(int i=0;i<array.length;i++){ 
			s=s+array[i];
			if(i<array.length-1){//放在在尾部多加seperator
				s += seperator;
			}
		}
		return s;
	}
	
	
	/*
	//测试主方法
	public static void main(String[] args){
		
		//1、数组内容前后对调
		int[] a={7, 9, 30, 3, 4};
		//int[] a={7, 9, 30, 3 };
		myArrayUtil test1 = new myArrayUtil();
		System.out.println("原数组：");
		for(int  i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		test1.reverseArray(a);
		System.out.println("\n转换后原数组：");
		for(int  i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		
		///2/去0操作///////////////
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int newArr2[];
		newArr2 = test1.removeZero(oldArr);
		System.out.println("\n去0后的数组：");
		for(int  i=0;i<newArr2.length;i++){
			System.out.print(newArr2[i]+" ");
		}
		
		//3、去重合并//////////////
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] newArr3 = test1.merge(a1, a2);
		System.out.println("\n去重合并后的数组：");
		for(int  i=0;i<newArr3.length;i++){
			System.out.print(newArr3[i]+" ");
		}
		
		//4、扩容
		int[]  oldArray4 = {2,3,6};
		int[]  newArray4 = test1.grow(oldArray4, 3);
		System.out.println("\n扩容后的数组：");
		for(int i=0;i<newArray4.length;i++){
			System.out.print(newArray4[i]+" ");
		}
		
		//4、斐波那契数列
		int[] newFibonacci = test1.fibonacci( 14);
		System.out.println("\n斐波那契数列数组：");
		for(int i=0;i<newFibonacci.length;i++){
			System.out.print(newFibonacci[i]+" ");
		}
		
		//5、素数数组
		int[] newPrims = test1.getPrimes(23);
		System.out.println("\n素数数组：");
		for(int i=0;i<newPrims.length;i++){
			System.out.print(newPrims[i]+" ");
		}
		
		//6、完数
		int[] perfectNumber = test1.getPerfectNumbers(29);
		System.out.println("\n完数数组：");
		for(int i=0;i<perfectNumber.length;i++){
			System.out.print(perfectNumber[i]+" ");
		}
		
		//7、用seperator 把数组 array给连接起来
		int[] array7= {3,8,9};
		String seperator="-";
		System.out.println("\n用- 把数组 array给连接起来后的数组：");
		 
		System.out.print(test1.join(array7, seperator));
		 
	}*/
	
}


