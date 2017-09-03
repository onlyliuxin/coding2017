import dp.bridge.v2.*;
import org.junit.Test;

/**
 * Created by lx on 2017/7/29.
 */
public class BridgeTest {

    @Test
    public void testBridge() {
        Shape r = new Rectangle(1, 2, 3, 4);
        Shape c = new Cicle(1, 2, 3);
        Drawing d1 = new DrawingGL1();
        Drawing d2 = new DrawingGL2();

        r.setDrawing(d1);
        r.draw();

        c.setDrawing(d1);
        c.draw();

        r.setDrawing(d2);
        r.draw();

        c.setDrawing(d2);
        c.draw();
    }
}
