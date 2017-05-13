import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IBM on 2017/5/8.
 */
public class TwoStackInOneArrayTest {
    private TwoStackInOneArray twoStackInOneArray;

    @Before
    public void startUp() {
        twoStackInOneArray = new TwoStackInOneArray();
        twoStackInOneArray.push1(2);
        twoStackInOneArray.push1(5);
        twoStackInOneArray.push1(1);


        twoStackInOneArray.push2(0);
        twoStackInOneArray.push2(4);
        twoStackInOneArray.push2(7);

    }

    @After
    public void tearDown() {

    }

    @Test
    public void pushTest() {
        twoStackInOneArray.push1(6);
        twoStackInOneArray.push1(8);

        twoStackInOneArray.push1(11);
        twoStackInOneArray.push1(50);
        //扩大
        twoStackInOneArray.push1(32);

        twoStackInOneArray.push2(29);
        System.out.println(twoStackInOneArray);
    }

    @Test
    public void popTest() {
        Object o1 = twoStackInOneArray.pop1();
        System.out.println(o1);
        twoStackInOneArray.push1(3);

        Object o2 = twoStackInOneArray.pop2();
        System.out.println(o2);
        twoStackInOneArray.push2(70);

        System.out.println(twoStackInOneArray);
    }

    @Test
    public void peekTest() {
        Object o1 = twoStackInOneArray.peek1();
        Object o2 = twoStackInOneArray.peek2();
        System.out.println(o1);
        System.out.println(o2);
        System.out.println(twoStackInOneArray);
    }

}
