package com.coderising.dp.bridge;

public class Rectangle extends Shape {
	private int x1, y1, x2, y2;

	public Rectangle(Drawing drawing, int x1, int y1, int x2, int y2) {
		// TODO Auto-generated constructor stub
		super(drawing);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		drawing.drawLine(x1, y1, x1, y2);
		drawing.drawLine(x1, y2, x2, y2);
		drawing.drawLine(x2, y2, x2, y1);
		drawing.drawLine(x2, y1, x1, y1);
	}

}
