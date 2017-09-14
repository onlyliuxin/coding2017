package com.coderings.dp.bridge;

public class GraphicLibrary1 implements GraphicLibrary{

	@Override
	public void drawLine(Shape shape) {
		Line line = (Line)shape;
		draw_a_line(line.getX1(), line.getY1(), line.getX2(), line.getY2());
	}

	@Override
	public void drawCircle(Shape shape) {
		Circle circle = (Circle)shape;
		draw_a_circle(circle.getX(), circle.getY(), circle.getY());
	}

	public void draw_a_line(int x1, int y1, int x2, int y2){
		System.out.println("graphic1 draw a line...");
	}
	public void draw_a_circle(int x,int y, int r){
		System.out.println("graphic1 draw a circle...");
	}

}
