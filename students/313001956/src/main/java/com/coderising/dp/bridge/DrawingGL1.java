package com.coderising.dp.bridge;

public class DrawingGL1 implements Drawing {
	GraphicLibrary1 lib1;

	public DrawingGL1(GraphicLibrary1 lib1) {
		this.lib1 = lib1;
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		lib1.draw_a_line(x1, y1, x2, y2);
	}

	@Override
	public void drawCircle(int x, int y, int r) {
		lib1.draw_a_circle(x, y, r);
	}

}
