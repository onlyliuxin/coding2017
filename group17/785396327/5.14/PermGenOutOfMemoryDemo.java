import java.util.ArrayList;
import java.util.List;

/**
 * Created by IBM on 2017/5/15.
 * -XX:PermSize=5M -XX:MaxPermSize=6M 无法呈现
 */
public class PermGenOutOfMemoryDemo {


    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
