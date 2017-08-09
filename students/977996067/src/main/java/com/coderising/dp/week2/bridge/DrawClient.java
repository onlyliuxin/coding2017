package com.coderising.dp.week2.bridge;

public class DrawClient {

    public static void main(String[] args) {
        Drawing drawing = new DrawingGL1();
        new Rectangle(drawing).draw();
    }
}
