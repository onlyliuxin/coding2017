package lessones02;
import others.Others;
import lessones01.LinkedListWithFloat;
public class ArrayUtil{
	public static void reverseArray(int[] arg_arr){
		for(int i = arg_arr.length/2;i>0;i--){
			//因为确定是int类型的数组所以可以这么用
			arg_arr[i-1] ^= arg_arr[arg_arr.length - i];
			arg_arr[arg_arr.length - i] ^= arg_arr[i-1];
			arg_arr[i-1] ^= arg_arr[arg_arr.length - i];
		}
	}
	public static int[] removeZero(int[] arg_arr){
		int zeroNum = 0;
		//这里和下面几个for都可以向上面那么写，性能(大概)会好点，但是看可读性还是算了
		for(int i=0;i<arg_arr.length;i++){if(arg_arr[i] == 0){zeroNum++;}}
		int[] newArray = new int[arg_arr.length - zeroNum];
		int f = 0;
		for(int x=0;x<arg_arr.length;x++){if(arg_arr[x] != 0){newArray[f++] = arg_arr[x];}}
		return newArray;
	}
	public static int[] merge(int[] arg_arr1,int[] arg_arr2){
		int s = 0;
		int f1=0;
		int f2=0;
		//感觉有相似代码，但是想不出来怎么优化
		for(;f1<arg_arr1.length&&f2<arg_arr2.length;s++){
			if(arg_arr1[f1] == arg_arr2[f2]){
				f1++;
				f2++;
			}else if(arg_arr1[f1] > arg_arr2[f2]){
				f2++;
			}else/*if(arg_arr1[f1] < arg_arr2[f2])*/{
				f1++;
			}
		}
		int[] arr = new int[s];
		for(s=0,f1=0,f2=0;f1<arg_arr1.length&&f2<arg_arr2.length;s++){
			if(arg_arr1[f1] == arg_arr2[f2]){
				arr[s] = arg_arr1[f1];
				f1++;
				f2++;
			}else if(arg_arr1[f1] > arg_arr2[f2]){
				arr[s] = arg_arr2[f2];
				f2++;
			}else/*if(arg_arr1[f1] < arg_arr2[f2])*/{
				arr[s] = arg_arr1[f1];
				f1++;
			}
		}
		return arr;
	}
	public static int[] grow(int[] arg_arr,int arg_size){
		int[] arr = new int[arg_arr.length + arg_size];
		int s = arg_arr.length;
		for(int i=0;i<s;i++){arr[i] = arg_arr[i];}
		int x = s;
		for(s+=arg_size;x<s;x++){arr[x] = 0;}
		return arr;
	}
	public static int[] fibonacci(int max){
		return makeFibonacci(0,1,1,max);
	}
	private static int[] makeFibonacci(int num,int a,int b,int max){
		if(a < max){
			int[] temp = makeFibonacci(num+1,b,a+b,max);
			temp[num] = a;
			return temp;
		}else{
			return new int[num];
		}
	}
	public static int[] getPrimes(int max){
		int i=0;
		for(;Others.PrimeGetter.get(i)<max;i++){}
		int[] arr = new int[i];
		for(i--;i>=0;i--){arr[i] = Others.PrimeGetter.get(i);}
		return arr;
	}
	private static int[] two_s_TimesEnd = {8,6,2,4};
	public static int[] getPerfectNumbers(int arg_num){
		LinkedListWithFloat<Integer> arr = new LinkedListWithFloat<Integer>();
		for(int i=2;;i++){
			int z = (int)Math.pow(2,i) - 1;
			int zz = z*two_s_TimesEnd[i&3];
			if(zz%10 == 8||zz%10 == 6){
				if(Others.PrimeGetter.isPrime(z)){
					int zzz = (int)Math.pow(2,i-1)*z;
					if(zzz<arg_num&&zzz>0){
						arr.add(zzz);
						System.out.println(zzz);
					}else{
						break;
					}
				}
			}
		}
		int[] numbers = new int[arr.size()];
		for(int i =0;i<arr.size();i++){numbers[i] = arr.get(i);}
		return numbers;
	}
	public static void main(String[] args){
		int[] a = fibonacci(15);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println("=========================");
		a = grow(a,5);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println("=========================");
		a = removeZero(a);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println("=========================");
		reverseArray(a);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println("=========================");
		a = getPrimes(1000);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println("=========================");
		a = getPerfectNumbers(40000000);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		System.out.println("=========================");
	}
}