package dp.bridge.v1;

/**
 * Created by lx on 2017/7/29.
 */
public abstract class Shape {
    private Drawing drawing;

    abstract void shape();

    public void setDrawing(Drawing drawing) {
        this.drawing = drawing;
    }

    public Drawing getDrawing() {
        return drawing;
    }
}
