package com.coderising.dp.bridge;

public class Rectangle extends Shape {

	@Override
	public void draw() {
		super.getDrawing().drawLine();
		System.out.println("I'm a Rectangle");
	}

}