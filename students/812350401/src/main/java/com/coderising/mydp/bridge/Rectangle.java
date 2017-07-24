package com.coderising.mydp.bridge;

/**
 * Created by thomas_young on 25/7/2017.
 */
public class Rectangle implements Shape {
    private Drawing drawing;
    private int x1, y1, x2, y2;

    @Override
    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    @Override
    public void draw() {
        drawing.drawLine(x1, y1, x1, y2);
        drawing.drawLine(x1, y1, x2, y1);
        drawing.drawLine(x1, y2, x2, y2);
        drawing.drawLine(x2, y1, x2, y2);
    }

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = x2;
    }

    public static void main(String[] args) {
        Shape r = new Rectangle(1,2,3,4);
        r.setDrawing(new DrawGL1());
        r.draw();
        r.setDrawing(new DrawGL2());
        r.draw();
    }
}
