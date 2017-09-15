package com.coderising.dp.bridge;

public class DrawLine extends Shape{
	int x1, y1, x2, y2;

	public DrawLine(Drawing drawing, int x1, int y1, int x2, int y2) {
		super(drawing);
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	public void draw() {
		this.drawing.drawLine(x1, y1, x2, y2);
	}
}
