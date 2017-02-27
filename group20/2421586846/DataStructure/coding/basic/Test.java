package basic;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		ArrayList arraylist1= new ArrayList();
		arraylist1.add(3);

		arraylist1.add(2);

		arraylist1.add(0,7);

		arraylist1.add(2,8);

		arraylist1.add(0,10);

		arraylist1.add(0,13);
		
		for (int i = 0 ;i <  arraylist1.size();i++){
			System.out.println(arraylist1.get(i));
		}
		
		/*
	    int[] arr = {1,2,3,4,5};  
	       
	    int[] copied = new int[10];  
	    System.arraycopy(arr, 0, copied, 1, 5);//5 is the length to copy  
	       
	    System.out.println(Arrays.toString(copied));  
		*/
	}

}
