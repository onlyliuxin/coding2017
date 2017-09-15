package com.coderising.mydp.bridge;

public class GraphicLibrary2 {
	public void drawLine(int x1,int x2,int y1,int y2){
		System.out.println("Library2_line: " + getLine(x1, y1, x2, y2));
	}
	public void drawCircle(int x,int y, int r){
        System.out.println("Library2_circle: " + "(" + x + ", " + y +"), r=" + r);
	}
	private String getLine(int x1,int y1,int x2,int y2) {
		return "(" + x1 + ", " + y1 + ")-----"+"(" + x2 + ", " + y2 + ")";
	}

	public static void main(String[] args) {
        GraphicLibrary2 library2 = new GraphicLibrary2();
        library2.drawLine(1,3,4,5);
        library2.drawCircle(0,2,4);
    }
}
