package com.github.Ven13.coding2017.array;

public class ArrayUtil {
		
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){

		int originalLen = origin.length;
		
	    int len = originalLen;
	    
	    int temp;
	    
	    for(int i = 0; i < (originalLen/2); i++){
	    	
	        temp = origin[len - i - 1];
	        
	        origin[len - i - 1] = origin[i];
	        
	        origin[i] = temp;
	        
	    }
	}
	
	/**
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		
		// ���������鳤�ȱ���
	    int newLength = 0;
	    // ��������鳤��ֵ
	    for (int i = 0; i < oldArray.length; i++) {
	    	
	    	if(oldArray[i] != 0) {
	    		
	    		newLength++;
	    		
	    	}
	    	
	    }
	        
	    // ����������
	    int[] newArray = new int[newLength];
	    // �����������±����
	    int n = 0;
	    // ת������
	    for (int i = 0; i < oldArray.length; i++) {
	    	
	    	if(oldArray[i] != 0) {
	    		
	    		newArray[n] = oldArray[i];// ת������
	            n++;// �������±�ƫ��
	    		
	    	}
	    	
	    }
	    
	    //�������ɵ�������
	    return newArray;
		
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		
		int[] newArray = new int[array1.length + array2.length];
		
		int k = 0;
		
		String inNum = "";
				
		for(int i = 0; i < array1.length; i++) {
			
			for(int j = 0; j < array2.length; j++) {
												
				if (array1[i] < array2[j]) {
					if (inNum.indexOf(array1[i] + "|") < 0) {
						newArray[k++] = array1[i]; 
						inNum += array1[i] + "|"; 
					}
					
				} else if (array1[i] == array2[j]) { 
					if (inNum.indexOf(array1[i] + "|") < 0) {
						newArray[k++] = array1[i]; 
						inNum += array1[i] + "|"; 
					}
				} else {
					if (i == array1.length - 1) {
						if (inNum.indexOf(array1[i] + "|") < 0) {
							newArray[k++] = array1[i]; 
							inNum += array1[i] + "|"; 
						}
					} else {
						if (inNum.indexOf(array2[j] + "|") < 0) {
							newArray[k++] = array2[j]; 
							inNum += array2[j] + "|"; 
						}
					}
					
				}
				
			}
			
		}

        return newArray;    
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
		int[] newArray = new int[oldArray.length + size];
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
		return null;
	}
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		
		int[] newArray = new int[max];
		
		int k = 0;
		
		boolean isN = true;
		
		if (max > 2) {
			
			for (int i = 2; i < max; i++) {
				
				isN = true;
				
				for (int j = 2; j < max; j++) {
					
		            if (i % j == 0 && i != j) {
		            	
		               isN = false;
		               
		            }
		        }
				
				if (isN) {
					
					newArray[k++] = i;
					
				}
				
	        }
			
		} else if (max == 2) {
			
			newArray[0] = 2;
			k++;
		} else {
			
			return null;
			
		}
		
		int[] newArray2 = new int[k];
		
		for(int i = 0; i < k; i ++) {
			
			newArray2[i] = newArray[i];
			
		}
		
		return newArray2;
	}
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max) {
		
		int i, j, k;
		
		int sum;  
		
		k = 0;
		
        for(i = 1; i <= max; i++) { 
        	
            
            sum = 0; 
            
            for(j = 1; j < i; j++) { 

                if(i % j == 0) { 
                	
                    sum += j; 
                    
                } 
                
            } 
            
                if(i == sum)  
                    k++;
        }  
		
		int[] newArray = new int[k];
		
		k = 0;
		
		for(i = 1; i <= max; i++) { 
        	
            
            sum = 0; 
            
            for(j = 1; j < i; j++) { 

                if(i % j == 0) { 
                	
                    sum += j; 
                    
                } 
                
            } 
            
                if(i == sum) {
                	
                	newArray[k] = i;
                	
                	k++;
                	
                }
                	
        } 
		

		return newArray;
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
		
		String str = "";
		
		for(int i = 0; i < array.length; i++) {
			
			str += array[i] + seperator;
			
		}
		
		str = str.substring(0, str.length() - 1);
		
		return str;
	}
		


}
