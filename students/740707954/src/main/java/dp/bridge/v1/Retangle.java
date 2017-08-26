package dp.bridge.v1;

/**
 * Created by lx on 2017/7/29.
 */
public class Retangle extends Shape {
    public Retangle(Drawing drawing) {
        setDrawing(drawing);
    }

    @Override
    public void shape() {
        getDrawing().drawLine();
    }

}
