package com.coderising.dp.bridge;

public class Test {

	public static void main(String[] args) {
		Shape rectangel=new Rectangle();
		rectangel.setDrawing(new DrawingGL1());
		rectangel.draw();
		

		Shape circle=new Circle();
		circle.setDrawing(new DrawingGL2());
		circle.draw();
	}

}
