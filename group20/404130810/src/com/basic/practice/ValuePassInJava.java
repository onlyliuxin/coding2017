package com.basic.practice;

public class ValuePassInJava {

	/*
	 * Pass the Simple value, etc int String...
	 * Change will NOT happened
	 * 
	*/ 
	public static void main(String[] args) {
		String s = new String("123");
		int i = 1;
		changeVal(i);
		System.out.println(i);
		
	}
	
	private static void changeVal(int i){
		i = 2;
	}
	
	/*
	 * Pass whole OBJECT, but change the Member variable
	 * Change will happened
	 */
	
	/*
	public static void main(String[] args) {
		Person p = new Person();
		p.age = 10;
		changeAge(p);
		System.out.println(p.age);
	}
	
	private static void changeAge(Person p){
		p.age = 20;
	}
	*/
	
}

class Person{
	int age;
}
