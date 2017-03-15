package algorithm;

import algorithm.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * ArrayUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 27, 2017</pre>
 */
public class ArrayUtilTest {
    ArrayUtil util = new ArrayUtil();

    /**
     * Method: reverseArray(int[] origin)
     */
    @Test
    public void testReverseArray() throws Exception {
//TODO: Test goes here...
        int[][] arrays = {
                {7, 9, 30, 3},
                {7, 9, 30, 3, 4},
                {5},
                {}
        };
        for (int[] a : arrays) {
            util.reverseArray(a);
        }

        int[][] result = {
                {3, 30, 9, 7},
                {4, 3, 30, 9, 7},
                {5},
                {}
        };
        Assert.assertArrayEquals(arrays, result);
    }

    /**
     * Method: removeZero(int[] oldArray)
     */
    @Test
    public void testRemoveZero() throws Exception {
//TODO: Test goes here...
        int[][] arrays = {
                {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5},
                {6, 6, 3, 5, 4},
                {0, 0, 0},
                {}
        };

        for (int i = 0; i < arrays.length; ++i) {
            arrays[i] = util.removeZero(arrays[i]);
        }

        int[][] result = {
                {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5},
                {6, 6, 3, 5, 4},
                {},
                {}
        };
        Assert.assertArrayEquals(arrays, result);
    }

    /**
     * Method: merge(int[] array1, int[] array2)
     */
    @Test
    public void testMerge() throws Exception {
//TODO: Test goes here...
        int[][] arrays1 = {
                {3, 5, 7, 8},
                {2, 3, 4},
                {1, 2, 3, 3, 4, 5},
                {1, 2, 2},
                {},
                {}
        };
        int[][] arrays2 = {
                {4, 5, 6, 7},
                {6, 7, 8, 9, 9},
                {4, 4, 5, 7},
                {},
                {2, 2, 3},
                {}
        };

        int[][] merged = new int[arrays1.length][];
        for (int i = 0; i < arrays1.length; ++i) {
            merged[i] = util.merge(arrays1[i], arrays2[i]);
        }

        int[][] result = {
                {3, 4, 5, 6, 7, 8},
                {2, 3, 4, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 7},
                {1, 2},
                {2, 3},
                {}
        };
        Assert.assertArrayEquals(merged, result);
    }

    /**
     * Method: grow(int [] oldArray, int size)
     */
    @Test
    public void testGrow() throws Exception {
//TODO: Test goes here...
        int[][] arrays = {
                {2, 3, 6},
                {},
                {1}
        };

        int[] size = {3, 3, 0};

        for (int i = 0; i < arrays.length; ++i) {
            arrays[i] = util.grow(arrays[i], size[i]);
        }

        int[][] result = {
                {2, 3, 6, 0, 0, 0},
                {0, 0, 0},
                {1}
        };
        Assert.assertArrayEquals(arrays, result);
    }

    /**
     * Method: fibonacci(int max)
     */
    @Test
    public void testFibonacci() throws Exception {
//TODO: Test goes here...
        int[] max = {0, 1, 2, 15};

        int[][] arrays = new int[max.length][];

        for (int i = 0; i < arrays.length; ++i) {
            arrays[i] = util.fibonacci(max[i]);
        }

        int[][] result = {
                {},
                {},
                {1, 1},
                {1, 1, 2, 3, 5, 8, 13}
        };
        Assert.assertArrayEquals(arrays, result);
    }

    /**
     * Method: getPrimes(int max)
     */
    @Test
    public void testGetPrimes() throws Exception {
//TODO: Test goes here...
        int[] max = {0, 1, 2, 3, 11};

        int[][] arrays = new int[max.length][];

        for (int i = 0; i < arrays.length; ++i) {
            arrays[i] = util.getPrimes(max[i]);
        }

        int[][] result = {
                {},
                {},
                {},
                {2},
                {2, 3, 5, 7}
        };
        Assert.assertArrayEquals(arrays, result);
    }

    /**
     * Method: getPerfectNumbers(int max)
     */
    @Test
    public void testGetPerfectNumbers() throws Exception {
//TODO: Test goes here...
        int[] max = {0, 6, 7, 496, 497};

        int[][] arrays = new int[max.length][];

        for (int i = 0; i < arrays.length; ++i) {
            arrays[i] = util.getPerfectNumbers(max[i]);
        }

        int[][] result = {
                {},
                {},
                {6},
                {6, 28},
                {6, 28, 496}
        };
        Assert.assertArrayEquals(arrays, result);
    }

    /**
     * Method: join(int[] array, String seperator)
     */
    @Test
    public void testJoin() throws Exception {
//TODO: Test goes here...
        int[][] arrays = {
                {3, 8, 9},
                {5},
                {6, 6},
                {}
        };

        String[] sep = {"-", "**", "", "---"};
        String[] joined = new String[arrays.length];

        for (int i = 0; i < arrays.length; ++i) {
            joined[i] = util.join(arrays[i], sep[i]);
        }

        String[] result = {
                "3-8-9",
                "5",
                "66",
                ""
        };
        Assert.assertArrayEquals(joined, result);
    }
} 
