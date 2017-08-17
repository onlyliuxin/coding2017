package com.coderising.dp.week2.bridge;

public class Circle extends Shape {

    public Circle(Drawing drawing) {
        super(drawing);
    }

    @Override
    public void draw() {
        getDrawing().drawLine();
        getDrawing().drawCircle();
        System.out.println("I am drawing a circle ...");
    }
}
