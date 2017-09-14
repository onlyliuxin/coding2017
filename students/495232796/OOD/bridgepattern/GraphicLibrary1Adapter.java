package com.coderising.dp.bridge;

public class GraphicLibrary1Adapter implements Drawing{
	GraphicLibrary1 glib = new GraphicLibrary1();
	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		this.glib.draw_a_line(x1, y1, x2, y2);
	}

	@Override
	public void drawCircle(int x, int y, int r) {
		this.glib.draw_a_circle(x, y, r);
	}

}
