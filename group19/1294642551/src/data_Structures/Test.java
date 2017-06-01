package data_Structures;

public class Test {

	public static void main(String[] args) {

		Stack s = new Stack();
		s.push("and");
		s.push("22");
		s.push(3);
		s.push(4);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());

	}

}
