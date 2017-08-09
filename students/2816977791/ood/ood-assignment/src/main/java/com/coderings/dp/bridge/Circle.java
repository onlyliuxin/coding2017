package com.coderings.dp.bridge;

public class Circle implements Shape {

	int x;
	int y;
	int r;

	private GraphicLibrary graphicLibrary;

	public Circle(int x, int y, int r, GraphicLibrary graphicLibrary) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.graphicLibrary = graphicLibrary;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getR() {
		return r;
	}

	@Override
	public void draw() {
		graphicLibrary.drawCircle(this);
	}

}
