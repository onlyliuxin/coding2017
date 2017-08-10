package dp.bridge.v2;

/**
 * Created by Administrator on 2017/8/10 0010.
 */
public class Rectangle implements Shape {

    private Drawing drawing;
    private int x1, y1, x2, y2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public void setDrawing(Drawing d) {
        this.drawing = d;
    }

    @Override
    public void draw() {
        drawing.drawLine();
    }
}
