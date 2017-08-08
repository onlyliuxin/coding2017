package com.coderising.dp.bridge;

public class Circle extends Shape {
	private int x, y, r;

	public Circle(Drawing drawing, int x, int y, int r) {
		super(drawing);
		this.x = x;
		this.y = y;
		this.r = r;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		drawing.drawCircle(x, y, r);
	}

}
