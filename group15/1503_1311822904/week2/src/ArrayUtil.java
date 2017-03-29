public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin){
		int length=origin.length;
		int[] reverse=new int[length];
		int i=0;
		for(;i<length;i++){
			reverse[i]=origin[length-i-1];
		}
		for(--i;i>-1;i--){
			origin[i]=reverse[i];
		}

	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		int length=oldArray.length;
		int[] index=new int[length];
		int i=0;
		int j=0;
		for(;i<length;i++){
			if(oldArray[i]!=0)
			index[j++]=i;
		}
		int[] b=new int[j];
		for(i=0;i<j;i++){
			b[i]=oldArray[index[i]];
		}
		return b;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		int array1Length=array1.length;
		int array2Length=array2.length;
		int length=array1Length+array2Length;
		int[] arr=new int[length];
		for(int i=0;i<array1Length;i++)
			arr[i]=array1[i];
		for(int i=0;i<array2Length;i++)
			arr[array1Length+i]=array2[i];

//排序
		for(int i = 1;i <length ; i ++){
			if(arr[i] < arr[i-1]){
				int temp = arr[i];
				int j;
				for(j = i-1; j >= 0 && arr[j] > temp; j --){
					arr[j+1] = arr[j];
				}
				arr[j+1] = temp;
			}
		}
		//去重合
		int j=0;
		for(int i = 1;i <length ; i ++){
			if(arr[i]!=arr[j]){
				arr[++j]=arr[i];
			}
		}
		//缩小数组尺寸 后面不要的去掉
		int[] c=new int[j+1];
		for(int i = 0;i <j+1 ; i ++){
			c[i]=arr[i];

		}
		return c;
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
	public static int[] grow(int [] oldArray,  int size){
		int oldLength=oldArray.length;
		int newLength=oldLength+size;
		int[] newArray=new int[newLength];
		System.arraycopy(oldArray,0,newArray,0,oldLength);
		return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){

		if(max<2){
			int[] a={};
			return a;
		}
		int[] a={1,1};
		int last=1;
		for(int length = 2;last<max; ){
			a=ArrayUtil.grow(a,1);
			length ++;
			a[length-1]=a[length-2]+a[length-3];
			//预测下一个
			last=a[length-1]+a[length-2];
		}
		return a;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) throws Exception {
		if(max<2)
			throw new Exception("必须大于2");
		int length=1;
		int[] a={2};
		for(int numb=3;numb<=max;numb++){
			if(isPrimeNumber(numb)){
				a=ArrayUtil.grow(a,1);
				length++;
				a[length-1]=numb;
			}

		}
		return a;
	}

	private static boolean isPrimeNumber(int numb) {

		for(int i=2;i<numb;i++){
			if(numb%i==0)
				return false;
		}
		return true;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max) throws Exception {
		if(max<1)
			throw new Exception("必须大于0");
		int length=1;
		int[] a={1};
		for(int numb=1;numb<=max;numb++){
			if(isPerfectNumber (numb)){
				a=ArrayUtil.grow(a,1);
				length++;
				a[length-1]=numb;
			}

		}
		return a;
	}

	private static boolean isPerfectNumber(int numb) {
		int a=0;
		for(int i=1;i<numb;i++){
			if(numb%i==0)
				a+=i;
		}
		if (a==numb)
			return true;
		return false;
	}

	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param
	 * @return
	 */
	public static String join(int[] array, String seperator) throws Exception {
		if(array==null)
			throw new Exception("请给数组");
		String s=array[0]+"";
		for (int i=1;i<array.length;i++){
			s+=seperator+array[i];
		}
		return s;
	}
	

}
