import dp.composite.*;
import org.junit.Test;

/**
 * Created by lx on 2017/7/29.
 */
public class CompositeTest {

    @Test
    public void testComp() {
        Picture picture1 = new Picture();
        Picture picture = new Picture();
        picture1.addShape(new Text());
        picture1.addShape(new Line());
        picture1.addShape(new Square());
        picture.addShape(picture1);
        picture.addShape(new Line());
        picture.addShape(new Rectangle());

        picture.draw();

    }
}
