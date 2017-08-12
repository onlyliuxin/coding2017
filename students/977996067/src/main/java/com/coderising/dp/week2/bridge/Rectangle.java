package com.coderising.dp.week2.bridge;

public class Rectangle extends Shape {

    public Rectangle(Drawing drawing) {
        super(drawing);
    }

    @Override
    public void draw() {
        getDrawing().drawLine();
        getDrawing().drawCircle();
        System.out.println("I am drawing a rectangle ...");
    }
}
