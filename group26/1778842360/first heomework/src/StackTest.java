//package javaTest;

public class StackTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Stack s=new Stack();
       s.push("world");
       s.push("java");
       s.push("world");
       Object o1=s.peek();
       System.out.println(o1);
       Object o=s.pop();
       System.out.println(o);
       int size=s.size();
       System.out.println(size);
       System.out.println(s.isEmpty());
	}

}

