package com.coderising.dp.bridge;

public abstract class Shape {
	protected Drawing drawing;
	public Shape(Drawing drawing) {
		// TODO Auto-generated constructor stub
		this.drawing = drawing;
	}
	
	public abstract void draw();
}
