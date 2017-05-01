package com.sort;
//冒泡排序
public class MaoPao {

	public static void main(String[] args) {
	//**************************冒泡***********************	
		int [] a = {3,4,2,1,5,6,0,40,23};
		boolean flag;
		do{
			flag = false;
			for(int i = 0; i < a.length;i++){
				if (i + 1 < a.length && a[i] > a[i + 1]){
					int temp = a[i];
					a[i] = a[i + 1 ];
					a[i + 1] = temp;
					flag = true;
				}
			}
			
		}while(flag);
		
		for(int i: a){
			System.out.print(i +" ");
		}
		System.out.println();
	//*****************************************************
	
	//**************************选择排序*********************
		int [] b = {3,4,2,1,5,6,0,40,23};
		
		int index = 0;
		while (index < b.length){
			int temp = b[index];
			int j = 0;
			boolean flag2 = false;
			for (int i = index ; i < b.length;i++){
				if (temp > b[i]){
					temp = b[i];
					j = i;
					flag2 = true;
				}
			}
			if (flag2){
				b[j] = b[index];
				b[index] = temp;
			}
			
			index++;
		}
		for(int i: b){
			System.out.print(i +" ");
		}
		System.out.println();
	//****************************************************
		
	//***********************插入排序***********************
		int [] c = {3,4,2,1,0};
		
		for (int i = 0; i < c.length; i++){
			int extract = c[i];
			for (int j = i - 1; j >= 0; j--){
				int current = c[j];
				if (current > extract){
					c[j + 1] = current;
					c[j] = extract;
				}else
					c[j + 1] = extract;
			}
		}
		for(int i: c){
			System.out.print(i +" ");
		}
	//****************************************************
	}
}
