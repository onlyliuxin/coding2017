package com.coderising.dp.week2.composition;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Picture implements Shape {

    private List<Shape> shapes = new CopyOnWriteArrayList<>();

    @Override
    public void draw() {
        shapes.forEach(Shape::draw);
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }
}
