package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {

	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		int n = elementData.length;
		int i = 0;
		while (elementData[i] != null) {
			i++;
		}
		if (i < n) {
			elementData[i] = o;
		} else {
			Object[] temp = Arrays.copyOf(elementData, n + 1);
			temp[n] = o;
			elementData = temp;
		}
		size++;
	}
	public void add(int index, Object o){
		int n = elementData.length;
		int i = 0;
		while (elementData[i] != null) {
			i++;
		}
		if (index < 0 || index > i) {
			System.out.println(index + " is invalid index!");
			return;
		}
		if (i < n) {
			for (int j = i; j > index; j--) {
				elementData[j] = elementData[j - 1];
			}
			elementData[index] = o;
		} else {
			Object[] temp = Arrays.copyOf(elementData, n + 1);
			for (int j = i; j > index; j--) {
				temp[j] = temp[j - 1];
			}
			temp[index] = o;
			elementData = temp;
		}
		size++;
	}
	
	public Object get(int index){
		int i = 0;
		while (elementData[i] != null) {
			i++;
		}
		if (index < 0 || index >= i) {
			System.out.println(index + " is invalid index!");
			return null;
		} else {
			return elementData[index];
		}
	}
	
	public Object remove(int index){
		int i = 0;
		while (elementData[i] != null) {
			i++;
		}
		if (index < 0 || index >= i) {
			System.out.println(index + " is invalid index!");
			return null;
		}
		Object result = elementData[index];
		for (int j = index; j < i; j++) {
			elementData[j] = elementData[j + 1];
		}
		size--;
		return result;
	}
	
	public int size(){
		return size;
	}

	public String toString() {
		int i = 0;
		while (elementData[i] != null) {
			i++;
		}
		String result = "";
		for (int j = 0; j < i - 1; j++) {
			result += elementData[j].toString() + ", ";
		}
		result += elementData[size - 1].toString();
		return result;
	}
	
	public Iterator iterator(){
		return null;
	}

	public static void main(String args[]){
		ArrayList list1 = new ArrayList();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		System.out.println(list1.toString());
		list1.add(5, "d");
		list1.add(1, "d");
		System.out.println(list1.toString());
		list1.add(4, "e");
		System.out.println(list1.toString());
		list1.get(5);
		System.out.println(list1.get(0));
		list1.remove(2);
		System.out.println(list1.toString());
		System.out.println(list1.size());
	}
	
}
