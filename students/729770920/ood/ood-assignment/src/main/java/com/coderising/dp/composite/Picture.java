package composite;

import java.util.ArrayList;
import java.util.List;

public class Picture implements Shape {
    private final List<Shape> shapes = new ArrayList<>();

    @Override
    public void draw() {
        shapes.forEach(shape -> draw());
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }
}
