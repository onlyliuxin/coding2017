package com.coderising.lru;

public class LRU {
	private static final int N = 5;  
	Object[] arr = new Object[N];
	
	private int size;
	
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isOutOfBoundary() {
		if(size >= N) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int indexOfElement(Object o) {
		for(int i = 0; i < size; i++){
			if(o == arr[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public Object push(Object o) {
		Object popObject = null;
		if(!isOutOfBoundary() && indexOfElement(o) == -1) {
			arr[size] = o;
			size++;
		}
		else if(isOutOfBoundary() && indexOfElement(o) == -1) {
			popObject = arr[0];
			System.arraycopy(arr, 1, arr, 0, size -1);
			arr[size - 1] = o;
		}
		else {
			int index = indexOfElement(o);
			System.arraycopy(arr, index + 1, arr, index, size - index - 1);
			arr[size -1] = o;
		}
		
		return popObject;
	}
	
    public void showMemoryBlock() {  
        for(int i=0; i<size; i++) {  
            System.out.print(arr[i] + "\t");  
        }  
    }  
    
	@Override
	public String toString() {
		String retStr = null;
		if(size > 0) {
			for(int i = 0; i < size; i++) {
				retStr += "," + arr[i];
			}
			retStr = retStr.substring(1);
		}
		return retStr;
		
	}
	
}
