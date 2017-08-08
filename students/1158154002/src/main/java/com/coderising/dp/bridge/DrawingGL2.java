package com.coderising.dp.bridge;

public class DrawingGL2 implements Drawing{

	@Override
	public void drawLine() {
		System.out.println("DrawingGL2.drawLine");
	}

	@Override
	public void drawCircle() {
		System.out.println("DrawingGL2.drawCircle");
	}

}
