package dp.bridge;

import org.junit.Test;

/**
 * Created by lx on 2017/7/29.
 */
public class BridgeTest {

    @Test
    public void testBridge() {
        Shape shape1 = new Circle(new DrawingGL1());
        Shape shape2 = new Retangle(new DrawingGL1());
        Shape shape3 = new Circle(new DrawingGL2());
        Shape shape4 = new Retangle(new DrawingGL2());
        shape1.shape();
        shape2.shape();
        shape3.shape();
        shape4.shape();
    }
}
