package com.coderising.dp.bridge;

public class testBrige {
	public static void main(String[] args) {
		GraphicLibrary1 lib1 = new GraphicLibrary1();
		GraphicLibrary2 lib2 = new GraphicLibrary2();

		DrawingGL1 drawingGL1 = new DrawingGL1(lib1);
		DrawingGL2 drawingGL2 = new DrawingGL2(lib2);
		int x = 8;
		int y = 9;
		int r = 6;
		Circle circle = new Circle(drawingGL1, x, y, r);
		circle.draw();

		circle = new Circle(drawingGL2, x, y, r);
		circle.draw();

		int x1 = 5;
		int y1 = 87;
		int x2 = 8;
		int y2 = 6;
		Rectangle rectangle = new Rectangle(drawingGL1, x1, y1, x2, y2);
		rectangle.draw();
		rectangle = new Rectangle(drawingGL2, x1, x2, y1, y2);
		rectangle.draw();
	}
}
