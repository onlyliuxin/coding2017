package com.coderings.dp.bridge;

/**
 * @author nvarchar
 *         date 2017/7/28
 */
public class ClientMain {
    public static void main(String[] args) {
        Shape line = new Line(1,2,1,2,new GraphicLibrary1());
        Shape line2 = new Line(1,2,1,2,new GraphicLibrary2());
        Shape circle = new Circle(1,2,2,new GraphicLibrary1());
        Shape circle2 = new Circle(1,2,1,new GraphicLibrary2());

        line.draw();
        line2.draw();
        circle.draw();
        circle2.draw();
    }
}
