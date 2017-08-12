package com.coderising.dp.bridge;

public class Circle extends Shape {

	@Override
	public void draw() {
		super.getDrawing().drawCircle();
		System.out.println("I'm a Line");

	}

}