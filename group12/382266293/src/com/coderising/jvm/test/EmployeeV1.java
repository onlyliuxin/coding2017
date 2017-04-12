package com.coderising.jvm.test;

public class EmployeeV1 {

	public static void main(String[] args) {
		EmployeeV1 p = new EmployeeV1("Andy", 29);
		p.sayHello();

	}
	private String name;

	private int age;

	public EmployeeV1(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void sayHello() {
		System.out.println("Hello , this is class Employee ");
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}
}