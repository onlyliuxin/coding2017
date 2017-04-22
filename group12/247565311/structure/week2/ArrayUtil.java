package structure.week2;
import structure.week1.ArrayList;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){ // time :O(n)
		int leno = origin.length;  
		for(int i=0;i<leno/2;i++){   
			int temp = origin[i];
			origin[i] = origin[leno-1-i];
			origin[leno-1-i] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	public int[] removeZero(int[] oldArray){ // time :O(n)
		int leno = oldArray.length,temp = 0;
		int []old = new int[leno];
		System.arraycopy(oldArray, 0, old, 0, leno);	// to not change the oldArray
		for(int i=0;i<leno;i++){
			if(old[i] == 0) temp += 1;
			else old[i-temp] = old[i];
		}
		int []newArray = new int[leno-temp];
		System.arraycopy(old, 0, newArray, 0, leno-temp);
		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){  // time :O(n)
		int len1 = array1.length,len2 = array2.length;
		int[]temparray = new int[len1+len2];
		int p1=0,p2=0,temp=-1;
		while(p1<len1 || p2 < len2){
			temp += 1;
			if(p1>=len1){
				temparray[temp] = array2[p2];
				p2 += 1;
				continue;
			}
			if(p2>=len2){
				temparray[temp] = array1[p1];
				p1 += 1;
				continue;
			}
			if(array1[p1] > array2[p2]){
				temparray[temp] = array2[p2];
				p2 += 1;
			}else{
				temparray[temp] = array1[p1];
				p1 += 1;
			}
		}
		temp = 0;
		for(int i=1;i<len1+len2;i++){
			if(temparray[i] == temparray[i-1]) temp += 1;
			else temparray[i-temp] = temparray[i];
		}
		int[]res = new int[len1+len2-temp];
		System.arraycopy(temparray, 0, res, 0, len1+len2-temp);
		return res;
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
		if(size<0) return oldArray;
		int[]newArray = new int[oldArray.length+size];
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
		if(max<=1) return new int[0];
		int last = 1,llast = 1;
		ArrayList<Integer> data = new ArrayList<Integer>();
		data.add(llast);
		data.add(last);
		while(last+llast<max){
			int temp = llast + last;
			data.add(temp);
			llast = last;
			last = temp;
		}
		int lend = data.size();
		int []res = new int[lend];
		for(int i=0;i<lend;i++){
			res[i] = data.get(i).intValue();
		}
		return res;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[]data = new int[max-2];
		ArrayList<Integer> li = new ArrayList<Integer>();
		int cur = 2;
		while(cur<max){
			li.add(cur);
			for(int i=cur;i*cur<max;i++){
				data[i*cur-2] = 1;		// note not primes
			}
			cur += 1;
			while(cur<max && data[cur-2] == 1) // skip not primes
				cur += 1;
		}
		int lenl = li.size();
		int []res = new int[lenl];
		for(int i=0;i<lenl;i++){
			res[i] = li.get(i).intValue();
		}
		return res;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		ArrayList<Integer> li = null,resli = new ArrayList<Integer>();
		for(int i=6;i<max;i++){
			li = getAllElem(i);
			int lenl = li.size(),temp = 0;
			for(int j =0;j<lenl;j++){
				temp += li.get(j);
			}
			if(temp == i) resli.add(i);
		}
		int lenr = resli.size();
		int []res = new int[lenr];
		for(int i=0;i<lenr;i++){
			res[i] = resli.get(i).intValue();
		}
		return res;
	}
	private ArrayList<Integer> getAllElem(int arg0){
		ArrayList<Integer> res = new ArrayList<Integer>();
		for(int i=1;i<arg0/2+1;i++){
			if(arg0%i == 0) res.add(i);
		}
		return res;
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
		if(array == null) return "";
		int lena = array.length;
		String res = "";

		for(int i=0;i<lena-1;i++){
			res += array[i];
			res += seperator;
		}
		res += array[lena-1];
		return res;
	}
}
