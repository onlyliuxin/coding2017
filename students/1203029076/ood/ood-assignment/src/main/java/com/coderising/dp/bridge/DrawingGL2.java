package com.coderising.dp.bridge;

public class DrawingGL2 implements Drawing {
	private GraphicLibrary2 graphicLibrary2;

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		graphicLibrary2.drawLine(x1, x2, y1, y2);
	}

	@Override
	public void drawCircle(int x, int y, int r) {
		// TODO Auto-generated method stub
		graphicLibrary2.drawCircle(x, y, r);
	}

}
