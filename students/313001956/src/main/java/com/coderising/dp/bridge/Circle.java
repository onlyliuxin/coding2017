package com.coderising.dp.bridge;

public class Circle implements Shape {

	private Drawing drawing;
	private int x;
	private int y;
	private int r;

	public Circle(Drawing drawing, int x, int y, int r) {
		this.drawing = drawing;
		this.x = x;
		this.y = y;
		this.r = r;

	}

	@Override
	public void draw() {
		drawing.drawCircle(x, y, r);
	}

}
