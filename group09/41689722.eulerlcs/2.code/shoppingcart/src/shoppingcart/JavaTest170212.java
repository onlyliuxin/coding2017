package shoppingcart;

public class JavaTest170212 {

	public static void main(String[] args) {
		t28();
	}

	public static void changeStr(String str) {
		str = "welcome";
	}

	public static void t28() {
		String str = "1234";
		changeStr(str);
		System.out.println(str);
	}

	public static void t34() {
		JavaTest170212 x = new JavaTest170212();
		JavaTest170212.Hello obj = x.new Hello("");
		obj.msg += ",World!";
		System.out.println(obj.msg);
	}

	class Hello {
		public String msg = "Hello";

		public Hello(String msg) {
			this.msg = msg;
		}
	}

}
