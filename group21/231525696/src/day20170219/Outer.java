package day20170219;

public class Outer {
	private int age;
	private String name;
	
	private void saySomething(){
		System.out.println("ssk");
	}
	
	private class InnerClass{
		public InnerClass(){
			name = "chengsc";
			age = 23;
		}
		
		private void display(){
			saySomething();
			System.out.println("name: " + name + ". age: " + age);
		}
		
		private Outer getOuter(){
			return Outer.this;
		}
	}
	public static void main(String[] args) {
		
		Outer outer = new Outer();
		Outer.InnerClass inner =  outer.new InnerClass();
		inner.display();
		inner.getOuter().saySomething();
	}
}
