package dp.bridge.v2;

/**
 * 图形库2
 * Created by lx on 2017/7/29.
 */
public class DrawingGL2 implements Drawing {
    @Override
    public void drawLine() {
        System.out.println("图形库2画线");
    }

    @Override
    public void drawCircle() {
        System.out.println("图形库2画圆");
    }
}
