package com.coderings.dp.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nvarchar
 *         date 2017/7/28
 */
public class CompositeShape extends ComponetShape{

    private List<Shape> shapes = new ArrayList<>();

    @Override
    public void draw() {
        for(Shape shape : shapes){
            shape.draw();
        }
    }

    @Override
    public void add(Shape shape) {
        shapes.add(shape);
    }

}
