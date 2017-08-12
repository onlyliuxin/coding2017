package com.coderising.mydp.bridge;

/**
 * Created by thomas_young on 25/7/2017.
 */
public class Circle implements Shape {
    private int x, y, r;
    private Drawing drawing;

    @Override
    public void draw() {
        drawing.drawCircle(x, y, r);
    }

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.drawing = drawing;
    }

    @Override
    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    public static void main(String[] args) {
        Shape c = new Circle(3,4,0);
        c.setDrawing(new DrawGL1());
        c.draw();
        c.setDrawing(new DrawGL2());
        c.draw();
    }
}
