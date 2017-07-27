package com.coderising.dp.week2.composition;

public class ShapeClient {

    public static void main(String[] args) {
        Picture subPicture = new Picture();
        Line line = new Line();
        subPicture.addShape(new Text());
        subPicture.addShape(line);
        subPicture.addShape(new Square());
        Picture parentPicture = new Picture();
        parentPicture.addShape(subPicture);
        parentPicture.addShape(line);
        parentPicture.addShape(new Rectangle());
        parentPicture.draw();
    }
}
