package dp.bridge.v1;

/**
 * Created by lx on 2017/7/29.
 */
public class Circle extends Shape {
    public Circle(Drawing drawing) {
        setDrawing(drawing);
    }

    @Override
    public void shape() {
        getDrawing().drawCircle();
    }


}
