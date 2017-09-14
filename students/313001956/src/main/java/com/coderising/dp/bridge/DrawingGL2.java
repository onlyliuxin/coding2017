package com.coderising.dp.bridge;

public class DrawingGL2 implements Drawing {
	GraphicLibrary2 lib2;

	public DrawingGL2(GraphicLibrary2 lib2) {
		this.lib2 = lib2;
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		lib2.drawLine(x1, x2, y1, y2);
	}

	@Override
	public void drawCircle(int x, int y, int r) {
		lib2.drawCircle(x, y, r);
	}

}
