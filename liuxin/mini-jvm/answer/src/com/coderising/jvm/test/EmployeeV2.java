package com.coderising.jvm.test;

public class EmployeeV2 {

	public final static String TEAM_NAME = "Dev Team";
	private String name;
	private int age;
	public EmployeeV2(String name, int age) {
        this.name = name;
        this.age = age;        
    }

	public void sayHello() {
		System.out.println("Hello , this is class Employee ");
		System.out.println(TEAM_NAME);
		System.out.println(this.name);
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

	public void isYouth() {
		if (age < 40) {
			System.out.println("You're still young");
		} else {
			System.out.println("You're old");
		}
	}
	
	

	public void testAdd() {
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			sum += i;
		}
		System.out.println(sum);
	}

	
	public static void main(String[] args) {
		EmployeeV2 p = new EmployeeV2("Andy", 35);
		p.sayHello();
		p.isYouth();
		p.testAdd();
	}
}