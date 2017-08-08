package com.coderising.dp.week2.bridge;

public abstract class Shape {

    private Drawing drawing;

    public Shape(Drawing drawing) {
        this.drawing = drawing;
    }

    public Drawing getDrawing() {
        return drawing;
    }

    protected abstract void draw();
}
