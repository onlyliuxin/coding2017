package shoppingcart;

public class Employee {
	private String id;
	private String name;
	private int age;
	private Hello hello;
	private static Hello staicHello;

	static {
		// staicHello = new Hello("");
	}

	@SuppressWarnings("unused")
	private Employee() {
		// NOP
	}

	public Employee(String name, int age) {
		this.id = "1001";
		this.name = name;
		this.age = age;
		this.hello = this.new Hello("");
	}

	@SuppressWarnings("unused")
	private String getID() {
		return this.id;
	}

	public void sayHello() {
		System.out.println("Hello, name =" + name + " age = " + age);
	}

	class Hello {
		public String msg = "Hello";

		public Hello(String msg) {
			this.msg = msg;
		}
	}
}