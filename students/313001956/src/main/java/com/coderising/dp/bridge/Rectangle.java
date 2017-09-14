package com.coderising.dp.bridge;

public class Rectangle implements Shape {
	private Drawing drawing;
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public Rectangle(Drawing drawing, int x1, int x2, int y1, int y2) {
		this.drawing = drawing;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	@Override
	public void draw() {
		drawing.drawLine(x1, y1, x2, y2);
	}

}
