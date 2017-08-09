package com.coderings.dp.composite;

/**
 * @author nvarchar
 *         date 2017/7/28
 */
public class ClientMain {
    public static void main(String[] args) {
        CompositeShape compositeShape = new CompositeShape();
        compositeShape.add(new Line());
        compositeShape.add(new Square());
        compositeShape.add(new Rectangle());
        compositeShape.add(new Text());
        compositeShape.draw();
    }
}
