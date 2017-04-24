package dataStruct.com.coderising.array;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by songbao.yang on 2017/3/2.
 *
 */
public class ArrayUtilTest {

    private  ArrayUtil arrayUtil;

    @Before
    public void setUp() throws Exception {
        arrayUtil = new ArrayUtil();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void reverseArray() throws Exception {

        int[][] actualAndExpected = {
                {}, {}, {0}, {0},
                {1,2,3,4,5,6}, {6,5,4,3,2,1},
                {7,9,30,3,4}, {4,3,30,9,7}
        };

        for (int i = 0; i < actualAndExpected.length; i += 2) {
            int[] acutal = actualAndExpected[i];
            int[] expected = actualAndExpected[i+1];
            arrayUtil.reverseArray(acutal);
            Assert.assertTrue("wrong index: " + String.valueOf(i), isArrayEqual(expected, acutal));
        }
    }

    @Test
    public void removeZero() throws Exception {
        int[][] actualAndExpected = {
                {}, {}, {0}, {},
                {1,0,3,0,5,0}, {1,3,5},
                {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}, {1,3,4,5,6,6,5,4,7,6,7,5}
        };

        for (int i = 0; i < actualAndExpected.length; i += 2) {
            int[] acutal = actualAndExpected[i];
            int[] expected = actualAndExpected[i+1];
            int[] ints = arrayUtil.removeZero(acutal);
            Assert.assertTrue("wrong index: " + String.valueOf(i), isArrayEqual(expected, ints));
        }
    }

    @Test
    public void merge() throws Exception {
        int[][] actualAndExpected = {
                {}, {}, {},
                {}, {0}, {0},
                {3,5,7,8}, {4,5,6,7},{3,4,5,6,7,8},
                {1,2,3,4,5,}, {6,7,8,9,10}, {1,2,3,4,5,6,7,8,9,10}
        };

        for (int i = 0; i < actualAndExpected.length; i += 3) {
            int[] array1 = actualAndExpected[i];
            int[] array2 = actualAndExpected[i+1];
            int[] expected = actualAndExpected[i+2];
            int[] result = arrayUtil.merge(array1, array2);
            Assert.assertTrue("wrong index: " + String.valueOf(i), isArrayEqual(expected, result));
        }

    }

    @Test
    public void grow() throws Exception {
        int[][] actualAndExpected = {
                {}, {},
                {1}, {},
                {5}, {0,0,0,0,0},
                {0},{2,3,6},
                {3}, {2,3,6,0,0,0}
        };

        for (int i = 0; i < actualAndExpected.length; i += 3) {
            int[] oldArray = actualAndExpected[i];
            int size = actualAndExpected[i+1][0];
            int[] expected = actualAndExpected[i+2];
            int[] newArray = arrayUtil.grow(oldArray, size);
            Assert.assertTrue("wrong index: " + String.valueOf(i), isArrayEqual(expected, newArray));
        }
    }

    @Test
    public void fibonacci() throws Exception {
        int[][] actualAndExpected = {
                {0}, {},
                {1}, {},
                {2}, {1,1},
                {3}, {1,1,2},
                {4}, {1,1,2,3},
                {15}, {1,1,2,3,5,8,13},
        };

        for (int i = 0; i < actualAndExpected.length; i += 2) {
            int max = actualAndExpected[i][0];
            int[] expected = actualAndExpected[i+1];
            int[] actual = arrayUtil.fibonacci(max);
            Assert.assertTrue("wrong index: " + String.valueOf(i), isArrayEqual(expected, actual));
        }
    }

    @Test
    public void getPrimes() throws Exception {
        int[][] actualAndExpected = {
                {-1}, {},
                {0}, {},
                {1}, {},
                {2}, {},
                {3}, {2},
                {4}, {2,3},
                {23}, {2,3,5,7,11,13,17,19},
        };

        for (int i = 0; i < actualAndExpected.length; i += 2) {
            int max = actualAndExpected[i][0];
            int[] expected = actualAndExpected[i+1];
            int[] actual = arrayUtil.getPrimes(max);
            Assert.assertTrue("wrong index: " + String.valueOf(i), isArrayEqual(expected, actual));
        }
    }

    @Test
    public void getPerfectNumbers() throws Exception {
        int[][] actualAndExpected = {
                {-1}, {},
                {0}, {},
                {1}, {},
                {2}, {},
                {7}, {6},
                {30}, {6,28},
                {500}, {6,28,496},
                {10000}, {6,28,496,8128}
        };

        for (int i = 0; i < actualAndExpected.length; i += 2) {
            int max = actualAndExpected[i][0];
            int[] expected = actualAndExpected[i+1];
            int[] actual = arrayUtil.getPerfectNumbers(max);
            Assert.assertTrue("wrong index: " + String.valueOf(i), isArrayEqual(expected, actual));
        }
    }

    @Test
    public void join() throws Exception {
        int[][] arrays = {
                {},
                {3,8,9},
                {1},
                {0,0,0,0,0},
                {1,2,3,4,5}
        };
        String[] separators = {"", "-", "+", "*", "00"};
        String[] expecteds = {
                "",
                "3-8-9",
                "1",
                "0*0*0*0*0",
                "1002003004005"
        };
        for (int i = 0; i < arrays.length; i++) {
            int[] array = arrays[i];
            String separator = separators[i];
            String expected = expecteds[i];
            String actual = arrayUtil.join(array, separator);
            Assert.assertTrue("wrong index: " + String.valueOf(i), expected.equals(actual));
        }
    }

    private boolean isArrayEqual(int[] expected, int[] actual){
        if (expected.length != actual.length){
            System.out.println("expected.length != actual.length");
            System.out.println("expected: " + Arrays.toString(expected));
            System.out.println("actual: " + Arrays.toString(actual));
            return false;
        }

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]){
                System.out.println("expected[i] != actual[i]");
                System.out.println("expected: " + Arrays.toString(expected));
                System.out.println("actual: " + Arrays.toString(actual));
                return false;
            }
        }

        return true;
    }

}