import java.util.ArrayList;
import java.util.List;

/**
 * Created by william on 2017/5/15.
 * 设置 -Xms20m -Xmx20m
 */
public class OutOfMemoryDemo {

    public static void main(String[] args) {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Object> list = new ArrayList<Object>();
        while (true) {
            list.add(new OutOfMemoryDemo());
        }
    }


}
