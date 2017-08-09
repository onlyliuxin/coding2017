package com.coderising.dp.bridge;

public class DrawCircle extends Shape{
	public int x, y, r;

	public DrawCircle(Drawing drawing, int x, int y, int r) {
		super(drawing);
		this.x =x;
		this.y = y;
		this.r = r;
	}
	public void draw() {
		this.drawing.drawCircle(x, y, r);
	}
}
