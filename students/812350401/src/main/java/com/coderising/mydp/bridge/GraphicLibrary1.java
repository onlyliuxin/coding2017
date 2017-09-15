package com.coderising.mydp.bridge;

public class GraphicLibrary1 {
	public void draw_a_line(int x1,int y1,int x2,int y2){
		System.out.println("Library1_line: " + get_a_line(x1, y1, x2, y2));
	}

	private String get_a_line(int x1,int y1,int x2,int y2) {
	    return "(" + x1 + ", " + y1 + ")-----"+"(" + x2 + ", " + y2 + ")";
    }

	public void draw_a_circle(int x,int y, int r) {
		System.out.println("Library1_circle: " + "(" + x + ", " + y +"), r=" + r);
	}

    public static void main(String[] args) {
        GraphicLibrary1 library1 = new GraphicLibrary1();
        library1.draw_a_line(1,3,4,5);
        library1.draw_a_circle(0,2,4);
    }

}
