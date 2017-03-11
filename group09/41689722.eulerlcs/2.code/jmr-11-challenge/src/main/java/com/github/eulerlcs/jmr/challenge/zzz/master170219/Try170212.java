package com.github.eulerlcs.jmr.challenge.zzz.master170219;

public class Try170212 {

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
		Try170212 x = new Try170212();
		Try170212.Hello obj = x.new Hello("");
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
