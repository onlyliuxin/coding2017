package array;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by william on 2017/2/27.
 */
public class ArrayUtilsTest {
    int[] array;
    private static final int RANGE = 9;
    private static final int BOUNDS = 10;
    private static Random random = new Random();


    @Before
    public void setUp() {
        array = randomArray(RANGE);
    }

    private int[] randomArray(int range) {
        int[] array = new int[range];
        for (int i = 0; i < range; i++) {
            array[i] = random.nextInt(BOUNDS);
        }
        return array;
    }

    @Test
    public void reverseArrayTest() {
        System.out.println(Arrays.toString(array));
        ArrayUtils.reserveArray(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void removeZeroTest() {
        System.out.println(Arrays.toString(array));
        int[] newArray = ArrayUtils.removeZero(array);
        System.out.println(Arrays.toString(newArray));
    }

    @Test
    public void mergeTest() {
        System.out.println(Arrays.toString(array));
        int[] array2 = {2, 5, 1, 6, 8};
        int[] merge = ArrayUtils.merge(array, array2);
        System.out.println(Arrays.toString(merge));
    }

    @Test
    public void growTest() {
        System.out.println(Arrays.toString(array));
        int[] result = ArrayUtils.grow(array, 4);
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void getPrimesTest() {
        int[] primes = ArrayUtils.getPrimes(54);
        System.out.println(Arrays.toString(primes));
    }

    @Test
    public void getPerfectNumbersTest() {
        int[] perfectNumbers = ArrayUtils.getPerfectNumbers(100000);
        System.out.println(Arrays.toString(perfectNumbers));
    }

    @Test
    public void joinTest() {
        String value = ArrayUtils.join(array, "-");
        System.out.println(value);
    }

    @Test
    public void fibonacciTest() {
        System.out.println(Arrays.toString(ArrayUtils.fibonacci(34)));
    }

}
