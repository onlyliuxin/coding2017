package com.coderising.mydp.composite;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thomas_young on 26/7/2017.
 */
public class Picture implements Shape {
    private List<Shape> shapes = new LinkedList<>();

    @Override
    public void draw() {
        System.out.println("Picture");
        for (Shape shape: shapes) {
            shape.draw();
        }
    }
    public void add(Shape shape) {
        shapes.add(shape);
    }

}
