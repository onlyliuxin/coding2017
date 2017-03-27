package basic;


import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.add(0);
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(4);
		
		LinkedList aa=ll;
		ll.add(0,10);
		System.out.println(aa.get(0));
	
	}
	public static void TestQueue(){
		Queue qu= new Queue();
		qu.enQueue(1);
		qu.enQueue(2);
		qu.enQueue(3);
		qu.enQueue(4);
		qu.enQueue(5);
		qu.enQueue(6);
		
		qu.enQueue(7);
		qu.enQueue(8);

		System.out.println(qu.deQueue());
		System.out.println(qu.deQueue());
		System.out.println(qu.deQueue());
	}
	public static void TestStack(){
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		System.out.println(s.pop());

		System.out.println(s.pop());
		System.out.println(s.pop());

		System.out.println(s.pop());
		System.out.println(s.pop());
		
	}
	public static void TestLinkedList(){
		LinkedList ll = new LinkedList();
		ll.add(0);
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(4);

		//ll.add(0,5);
		//ll.add(0,7);
		ll.removeLast();
		ll.removeLast();
		ll.removeLast();
		int size = ll.size();
		for (int i=0;i< size;i++){

			System.out.println(ll.get(i));
		}
	}
	
	public static void TestArrayList(){
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
		System.out.println("ok");
		arraylist1.remove(0);

		arraylist1.remove(1);
		
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
