package com.coderising.dp.bridge;

public abstract class Shape {
	public Drawing drawing;
	public Shape(Drawing drawing) {
		this.drawing = drawing;
	}
	public void draw() {
	}
}
