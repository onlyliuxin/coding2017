package cn.test;

public class Test {

	public static void main(String[] args) {
		String name = "name";
		String n ="set"+name.substring(0,1).toUpperCase()+name.substring(1);
		
		System.out.println(n);
	}
}
