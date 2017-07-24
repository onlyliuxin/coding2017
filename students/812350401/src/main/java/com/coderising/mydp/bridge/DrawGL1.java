package com.coderising.mydp.bridge;

/**
 * Created by thomas_young on 25/7/2017.
 */
public class DrawGL1 implements Drawing {
    private GraphicLibrary1 gl1 = new GraphicLibrary1();

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        gl1.draw_a_line(x1, y1, x2, y2);
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        gl1.draw_a_circle(x, y, r);
    }

    public static void main(String[] args) {
        DrawGL1 d1 = new DrawGL1();
        d1.drawCircle(4,5,1);
        d1.drawLine(1,2,3,4);
    }
}
