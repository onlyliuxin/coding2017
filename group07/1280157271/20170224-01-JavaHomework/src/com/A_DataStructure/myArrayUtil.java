package com.A_DataStructure;
 

public class myArrayUtil {
	
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int[] temp = new int[origin.length];
		int i=0,j=0;
		int originLegth=origin.length-1;
		int originLegthTemp=originLegth; 
		for( i=0;i<origin.length;i++){
			temp[i] = origin[i];
		}
		//1������������û���ǰ��
		for( i=0;i<origin.length/2 ;i++){ 
			origin[i] = origin[originLegth--];
		} 
		//2��������ǰ���û������� 
		for( i=0;i<origin.length/2 ;i++){ 
			origin[originLegthTemp--] = temp[i];
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
		int[] newArray= new int[oldArray.length];
		int j=0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i] != 0){ 
				 newArray[j++]=oldArray[i];
			}
		} 
		//ȥβ��0����
		int[] newArray2 = new int[j];//j++,����j��ʱ��������Сһ�� 
		for(int i=0;i<j;i++){
			newArray2[i]=newArray[i];
		}
		return newArray2;
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int a1Length = array1.length;
		int a2Length = array2.length;
		int newLength = a1Length+a2Length; //�����鳤�� 
		int[] newArr = new int[newLength];
		
		//1���Ȱ�a1ֱ�Ӹ��ƽ�ȥ
		for(int i=0;i<a1Length;i++){ 
			newArr[i] = array1[i];
		}
		//2����a2�в��ظ���ֵ���ƽ�ȥ
		int newArrSize = a1Length;
		for(int j=0;j<a2Length;j++){
			int mark=1;  //��־���ظ�Ԫ�� 
			for(int i=0;i<a1Length;i++){ 
				if(array2[j] == array1[i]){
					 mark = 0;
				 
				}
			}//��forѭ��һ�αȽ���һ�Σ�array2�е�һ��Ԫ����array1���е�����Ԫ���Ƿ�����ͬ��
			if(mark == 1){//array2�еĵ�j-1��Ԫ��û���ظ�������newArray��
				newArr[newArrSize++] = array2[j];//newArr���Ѿ�������array1
			}
		}
	 
		newArr = this.removeZero(newArr);
		//4��ð������
		int temp;
		for(int i=0;i<newArr.length-1;i++){//n������һ����һ�����������Ҫn-1�ˣ����һ��������Ҫ�ţ�
			for(int j=1;j<newArr.length-i;j++){//ÿһ�������Ƚϣ�ֻ��Ƚ�û�бȽϵ���
				if(newArr[j-1]>newArr[j]){//���ǰ�߱Ⱥ��ߴ󣬾ͽ���λ��
					temp = newArr[j-1];
					newArr[j-1] = newArr[j];
					newArr[j] = temp;
				}
			}
		}
		
		return  newArr;
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
		int[] newArray = new int[oldArray.length+size];
		//System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
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
		int[] fibonacci = new int[max+1];//���ɿ��������еĳ��������ϲ���maxֵ������
		if(max<=1){
			System.out.println("�������󣡣�");
			return null;
		}else{
			int temp;
			fibonacci[0]=1;//����maxΪ����[0]��Ϊ1(maxȡֵ[1-����]
			int size=1;  //fibonacci��������ʹ�õĿռ�
			if(max>1){ //2������
				fibonacci[1]=1; 
				for(int i=2;i<max;i++){//��ʼʱfibonacci[i]=1<max=2��
					temp = fibonacci[i-1]+fibonacci[i-2]; 
					if(temp>=max){
						break;//����ѭ��
					}
					fibonacci[i] = temp;//����max�Ļ�������ȥ�ˣ������������
				}
			}
		}
		return this.removeZero(fibonacci);//ȥ0����
	}
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] newPrims = new int[max];
		int size=0;
		
		if(max<2){
			return null;
		}else{
			 for(int i=2;i<max;i++){//Ҫ�жϵ���
				int mark = 1; //������ǣ�
				for(int j=2;j<i;j++){//С�ڱ����������г���
					if(i%j ==0){
						mark = 0;
						break;
					}
				}
				if(mark == 1){
					newPrims[size++] = i;
				}
			 }
		}
		return this.removeZero(newPrims);
	}
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] perfectNumber = new int[max];  
		int pNumSize=0;
		
		for(int i=1;i<max;i++){
			int sum=0; 
			for(int j=1;j<i;j++){//���� 
				if(i%j==0){//˵��j��i��һ������
					 sum +=j;
					
				}
			}//��ѭ������һ�Σ��ҳ���i���������� 
			if(sum == i){//�͵���i�ĳ���֮��
				 perfectNumber[pNumSize++]=i;
			}
		}
		
		return this.removeZero(perfectNumber);
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
		String s="";
		for(int i=0;i<array.length;i++){ 
			s=s+array[i];
			if(i<array.length-1){//������β�����seperator
				s += seperator;
			}
		}
		return s;
	}
	
	
	/*
	//����������
	public static void main(String[] args){
		
		//1����������ǰ��Ե�
		int[] a={7, 9, 30, 3, 4};
		//int[] a={7, 9, 30, 3 };
		myArrayUtil test1 = new myArrayUtil();
		System.out.println("ԭ���飺");
		for(int  i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		test1.reverseArray(a);
		System.out.println("\nת����ԭ���飺");
		for(int  i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		
		///2/ȥ0����///////////////
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int newArr2[];
		newArr2 = test1.removeZero(oldArr);
		System.out.println("\nȥ0������飺");
		for(int  i=0;i<newArr2.length;i++){
			System.out.print(newArr2[i]+" ");
		}
		
		//3��ȥ�غϲ�//////////////
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] newArr3 = test1.merge(a1, a2);
		System.out.println("\nȥ�غϲ�������飺");
		for(int  i=0;i<newArr3.length;i++){
			System.out.print(newArr3[i]+" ");
		}
		
		//4������
		int[]  oldArray4 = {2,3,6};
		int[]  newArray4 = test1.grow(oldArray4, 3);
		System.out.println("\n���ݺ�����飺");
		for(int i=0;i<newArray4.length;i++){
			System.out.print(newArray4[i]+" ");
		}
		
		//4��쳲���������
		int[] newFibonacci = test1.fibonacci( 14);
		System.out.println("\n쳲������������飺");
		for(int i=0;i<newFibonacci.length;i++){
			System.out.print(newFibonacci[i]+" ");
		}
		
		//5����������
		int[] newPrims = test1.getPrimes(23);
		System.out.println("\n�������飺");
		for(int i=0;i<newPrims.length;i++){
			System.out.print(newPrims[i]+" ");
		}
		
		//6������
		int[] perfectNumber = test1.getPerfectNumbers(29);
		System.out.println("\n�������飺");
		for(int i=0;i<perfectNumber.length;i++){
			System.out.print(perfectNumber[i]+" ");
		}
		
		//7����seperator ������ array����������
		int[] array7= {3,8,9};
		String seperator="-";
		System.out.println("\n��- ������ array����������������飺");
		 
		System.out.print(test1.join(array7, seperator));
		 
	}*/
	
}


