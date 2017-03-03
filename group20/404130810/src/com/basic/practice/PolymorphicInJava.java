package com.basic.practice;

/**
 * 
 * @author Wu Alvin
 * Java Polymorphic Only represent in method level
 *
 */

class Fruit{
	String name = "Fruit";
	public void print(int i){
		System.out.println("Fruit" + i);
	}
}


class Apple extends Fruit{
	String name = "Apple";
	public void print(int i){
		System.out.println("Apple" + i);
	}
}


public class PolymorphicInJava {
	
	public static void main(String[] args) {
		Apple apple = new Apple();
		apple.print(100);
		//Apple100
		System.out.println(apple.name);
		//Apple
		Fruit fruit = apple;
		fruit.print(100);
		//Apple100
		System.out.println(fruit.name);
		//Fruit
	}

}
