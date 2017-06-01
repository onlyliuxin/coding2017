package overflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class OverflowTest extends Thread {

	static void testString() {
		long count = 0;
		long k = 0;
		String b = "";
		List<String> list = new ArrayList<String>();    
		while(true) {
			count++;
			Random x = new Random();
			String a = new String(x.nextDouble() + UUID.randomUUID().toString()).intern();
			b = a;
			list.add(a);
			if (count % 1000000 == 0) {
				k++;
				System.out.println(k + "k String");
			}
		}
	}
	
	static int feb(int i) {
		if (i == 0 || i == 1) {
			return i;
		} else {
			return feb(i) + feb(i+1);
		}
	}
	String a;
	
	
	public void run() {
		genOverFlow();
	}
	
	public OverflowTest(String a) {
	}
	static ArrayList<String> s = new ArrayList<String>();
	
	void genOverFlow() {
		testString();
		 List<String> list = new ArrayList<String>();    
	     while(true){    
	             list.add(UUID.randomUUID().toString().intern());    
	     }  
	}
  
	String testMemory() {

		String a = "";
		Integer i = 0;
		while(true) {
			String b = new String("" + new Integer(i+1));
			s.add(b);
			a = b + " ok";
			if (i % 10000000 == 2) {
				System.out.println(a);
			}
		}

	}
	
	public static void main(String[] args) {
		
		
		for (int i = 0; i < 12; i++) {
			Thread a = new OverflowTest("ok");
			a.start();
			System.out.println(i + " is running");
		}

		//feb(2);
		//Exception in thread "main" java.lang.StackOverflowError
		//at overflow.OverflowTest.feb(OverflowTest.java:24)
		
		//testMemory();
//		Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//		at java.util.Arrays.copyOf(Unknown Source)
//		at java.util.Arrays.copyOf(Unknown Source)
//		at java.util.ArrayList.grow(Unknown Source)
		
	}
}
