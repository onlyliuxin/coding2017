package dp.bridge.v2;

/**
 * Created by Administrator on 2017/8/10 0010.
 */
public class Cicle implements Shape {

    private Drawing drawing;
    private int x1, x2, r;

    public Cicle(int x1, int x2, int r) {
        this.x1 = x1;
        this.x2 = x2;
        this.r = r;
    }

    @Override
    public void setDrawing(Drawing d) {
        this.drawing = d;
    }

    @Override
    public void draw() {
        drawing.drawCircle();
    }
}
