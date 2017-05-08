package basic.array;

import java.util.Arrays;


class ArrayUtil {

    /**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin 原数组
	 */
	void reverseArray(int[] origin){
        if(origin!=null && origin.length>0){
            int i, n;
            for(i=0, n=origin.length-1; i<origin.length/2; i++,n--){
                int temp;
                temp = origin[i];
                origin[i] = origin[n];
                origin[n] = temp;
            }
        }
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray 原数组
	 * @return 返回去掉0的新数字
	 */
	int[] removeZero(int[] oldArray){
        if(oldArray!=null && oldArray.length>0) {
            int index = 0;
            int length = 0;
            int[] temp = new int[oldArray.length];
            for(int i=0; i<oldArray.length; i++){
                if(oldArray[i]!=0){
                    temp[index++] = oldArray[i];
                }else{
                    length++;
                }
            }
            return Arrays.copyOf(temp,temp.length-length);
        }else{
            return null;
        }
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1 第一个数组
	 * @param array2 第二个数组
	 * @return 整合的数组
	 */
	int[] merge(int[] array1, int[] array2){
        if(array1!=null && array2!=null){
            int length = array1.length+ array2.length;
            int[] merges = new int[length];
            int i, j, index, split;
            split = index = 0;
            for(i=0,j=0; i<array1.length&&j<array2.length; index++){
                if(array1[i]<array2[j]){
                    merges[index] = array1[i];
                    i++;
                }else if(array1[i]>array2[j]){
                    merges[index] = array2[j];
                    j++;
                }else{
                    merges[index] = array2[j];
                    i++;
                    j++;
                    split++;
                }
            }
            while(i<array1.length){
                if(array1[i]==merges[index-1]){
                    split++;
                    i++;
                }else{
                    merges[index++] = array1[i++];

                }
            }
            while(j<array2.length){
                if(array2[j]==merges[index-1]){
                    split++;
                    j++;
                }else{
                    merges[index++] = array2[j++];

                }
            }
            return  Arrays.copyOf(merges,merges.length-split);
        }else{
            return null;
        }

	}

	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray 原数组
	 * @param size 大小
	 * @return 扩容后的数组
	 */
	int[] grow(int [] oldArray,  int size){
        if(oldArray==null){
            return null;
        }
		return Arrays.copyOf(oldArray,oldArray.length+size);
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max 最大值
	 * @return 斐波那契数组
	 */
	 int[] fibonacci(int max){
		int[] result = new int[max];
        if(max==1){
            return new int[0];
        }
        if(max<=0){
			return null;
		}

        int i = 1;
		int j = 0;
		while(j<max){
			j = fibonac(i);
			if(j<max) {
				result[i-1] = j;
				i++;
			}
		}
		return Arrays.copyOf(result,i-1);
	}

	private static int fibonac(int n){
		if(n==1||n==2){
			return 1;
		}else{
			return fibonac(n-1)+fibonac(n-2);
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max 最大值
	 * @return 小于给定最大值max的所有素数数组
	 */
	 int[] getPrimes(int max){
		 if(max<=1){
			 return null;
		 }
		int[] result = new int[max];
		int index = 0;
		for(int i=2; i<max; i++){
			boolean flag = true;
			for(int j=2; j<Math.sqrt(i); j++){
				if(i%j==0){
					flag = false;
					break;
				}
			}
			if(flag){
				result[index++] = i;
			}
		}
		return Arrays.copyOf(result,index);
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max 最大值max
	 * @return 返回一个数组， 数组中是小于max 的所有完数
	 */
	int[] getPerfectNumbers(int max){
		if(max<=0){
			return null;
		}
		int[] result = new int[max];
		int length = 0;
		for(int i=1; i<max; i++){
			if(checkPerfectNumbers(i)){
				result[length++] = i;
			}
		}
		return Arrays.copyOf(result, length);
	}

	private  boolean checkPerfectNumbers(int num){
		int total  = 0;
		for(int i=1; i<num; i++){
			if(num%i==0){
				total += i;
			}
		}
		return total==num ;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array  数组
	 * @param seperator  连接符
	 * @return 连接后的数组
	 */
	String join(int[] array, String seperator){
		if(array.length>0) {
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < array.length; i++) {
				stringBuffer.append(array[i]);
				if (i != array.length - 1) {
					stringBuffer.append(seperator);
				}
			}
			return stringBuffer.toString();
		}else{
			return null;
		}
	}

}
