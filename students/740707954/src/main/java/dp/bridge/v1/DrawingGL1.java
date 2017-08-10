package dp.bridge.v1;

/**
 * 图形库1
 * Created by lx on 2017/7/29.
 */
public class DrawingGL1 implements Drawing{
    @Override
    public void drawLine() {
        System.out.println("图形库1画线");
    }

    @Override
    public void drawCircle() {
        System.out.println("图形库1画圆");
    }
}
