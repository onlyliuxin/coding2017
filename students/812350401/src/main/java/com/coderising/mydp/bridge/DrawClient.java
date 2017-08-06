package com.coderising.mydp.bridge;

/**
 * Created by thomas_young on 25/7/2017.
 */
public class DrawClient {
    public static void main(String[] args) {
        Shape r = new Rectangle(1,2,3,4);
        Shape c = new Circle(2,3,4);
        Drawing d1 = new DrawGL1();
        Drawing d2 = new DrawGL2();

        r.setDrawing(d1);
        r.draw();
        System.out.println();
        r.setDrawing(d2);
        r.draw();
        System.out.println();

        c.setDrawing(d1);
        c.draw();
        System.out.println();
        c.setDrawing(d2);
        c.draw();
    }
}
