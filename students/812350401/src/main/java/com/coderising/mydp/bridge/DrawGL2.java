package com.coderising.mydp.bridge;

/**
 * Created by thomas_young on 25/7/2017.
 */
class DrawGL2 implements Drawing {
    private GraphicLibrary2 gl2 = new GraphicLibrary2();
    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        gl2.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        gl2.drawCircle(x, y, r);
    }

    public static void main(String[] args) {
        DrawGL2 d2 = new DrawGL2();
        d2.drawCircle(4,5,1);
        d2.drawLine(1,2,3,4);
    }
}
