package week2;
import week1.ArrayList;

public class ArrayUtil {
	
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
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
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
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
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
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
	 * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size
	 * ע�⣬�������Ԫ��������������Ҫ����
	 * ���� oldArray = [2,3,6] , size = 3,�򷵻ص�������Ϊ
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
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
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
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
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
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
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
	 * ��seperator ������ array����������
	 * ����array= [3,8,9], seperator = "-"
	 * �򷵻�ֵΪ"3-8-9"
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
