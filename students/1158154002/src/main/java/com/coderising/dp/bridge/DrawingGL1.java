package com.coderising.dp.bridge;

public class DrawingGL1 implements Drawing{

	@Override
	public void drawLine() {
		System.out.println("DrawingGL1.drawLine");
	}

	@Override
	public void drawCircle() {
		System.out.println("DrawingGL1.drawCircle");
	}

}
