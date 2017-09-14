package com.coderising.dp.week2.bridge;

public class DrawingGL1 implements Drawing {

    @Override
    public void drawLine() {
        System.out.println("I am drawing line 1 ...");
    }

    @Override
    public void drawCircle() {
        System.out.println("I am drawing circle 1 ...");
    }
}
