package com.coderings.dp.bridge;

public class GraphicLibrary2  implements GraphicLibrary{
	@Override
	public void drawLine(Shape shape) {
		Line line = (Line)shape;
		drawLine(line.getX1(), line.getX2(), line.getY1(), line.getY2());
	}

	@Override
	public void drawCircle(Shape shape) {
		Circle circle = (Circle)shape;
		drawCircle(circle.getX(), circle.getY(), circle.getY());
	}

	public void drawLine(int x1, int x2, int y1, int y2){
		System.out.println("graphic2 draw a line...");
	}
	public void drawCircle(int x,int y, int r){
		System.out.println("graphic2 draw a circle...");
	}

}
