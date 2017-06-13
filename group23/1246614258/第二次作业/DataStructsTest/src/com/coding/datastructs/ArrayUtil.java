/**
 * 
 */
package com.coding.datastructs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ArrayUtil {
	
	
	public void reverseArray(int[] origin){
		for (int i=0; i<origin.length/2; i++) {
            int temp = origin[i];
            origin[i] = origin[origin.length-1-i];
            origin[origin.length-1-i] = temp;
    }
	}
	
	
	
	public int[] removeZero(int[] oldArray){
		int count=0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				oldArray[count++]=oldArray[i];
			}else{
				continue;
			}
		}
		return Arrays.copyOfRange(oldArray, 0, count);
	}
	
	
	
	public int[] merge(int[] array1, int[] array2){
		int length = array1.length+array2.length;
		int[] newArray = new int[length];
		System.arraycopy(array1, 0, newArray, 0,array1.length);
		System.arraycopy(array2, 0, newArray, array1.length,array2.length);
		Set<Integer> temp = new HashSet<Integer>();
		for(Integer i : newArray)
			temp.add(i); 
        for (int i = 0; i < temp.size(); i++) {  
        	newArray[i]=(int)temp.toArray()[i];
        }
        Arrays.sort(Arrays.copyOfRange(newArray, 0, temp.size()));
		return  Arrays.copyOfRange(newArray, 0, temp.size());
	}
	
	public int[] grow(int [] oldArray,  int size){
			int[] target = new int[oldArray.length+size];
			System.arraycopy(oldArray, 0, target, 0, oldArray.length);
			return target;
	}
	
	
	public int[] fibonacci(int max){
		ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(1);
        if(max<=1){
        	return new int[0];
        }
        for (int i = 2; i < max; i++) {
        	int a = list.get(i-1)+list.get(i-2);
            if(a<max){
            	list.add(a);
            }else{
            	break;
            }
        }
        int[] newArray = new int[list.size()];
        for(int i=0;i<list.size();i++){
        	newArray[i]=(int)list.get(i);
        }
		return newArray;
	}
	
	
	public int[] getPrimes(int max){
		ArrayList<Integer> list = new ArrayList<Integer>();
		int j=1;
		for(int i=2;i<100;i++){
			j=i;
			while(j>0){
				if(i%j==0&&i!=j&&j!=1){
					break;
				}else{
					j--;
				}
			}
			if(j==0){
				list.add(i);
			}
		}
		int[] newArray = new int[list.size()];
        for(int i=0;i<list.size();i++){
        	newArray[i]=(int)list.get(i);
        }
		return newArray;
	}
	
	
	public int[] getPerfectNumbers(int max){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i < max; i++)  
        {  
            int sum = 0;  
            for(int j = 1; j < i; j++)  
            {  
                if(i % j == 0)  
                {  
                    sum = sum + j;    
                }     
            }  
            if(sum == i)  
            {  
                list.add(i);  
            }   
        }
		int[] newArray = new int[list.size()];
        for(int i=0;i<list.size();i++){
        	newArray[i]=(int)list.get(i);
        }
		return newArray;
	}
	
	public String join(int[] array, String seperator){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<array.length;i++){
			str.append(array[i]).append(seperator);
		}
		return str.substring(0, str.length()-1).toString();
	}
	

}
