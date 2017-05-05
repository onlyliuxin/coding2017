import coding.ArrayUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jiaxun
 */
public class TestArrayUtil {

    private ArrayUtil arrayUtil;

    @Before
    public void setUp() {
        arrayUtil = new ArrayUtil();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testReverseArrayOdd() {
        int[] actualArray = {6, 2, 5, 8, 7};
        arrayUtil.reverseArray(actualArray);
        int[] expectedArray = {7, 8, 5, 2, 6};
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testReverseArrayEven() {
        int[] actualArray = {5, 3, 6, 7};
        arrayUtil.reverseArray(actualArray);
        int[] expectedArray = {7, 6, 3, 5};
        Assert.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testRemoveZero() {
        int[] oldArray = {4, 5, 0, 7, 0, 3};
        int[] newArray = arrayUtil.removeZero(oldArray);
        Assert.assertEquals(newArray.length, 4);
        Assert.assertEquals(newArray[2], 7);
    }

    @Test
    public void testMerge() {
        int[] array1 = {3, 5, 7,8};
        int[] array2 = {4, 5, 6,7};
        int[] array3 = arrayUtil.merge(array1, array2);
        Assert.assertEquals(array3.length, 6);
        int[] expectArray = {3, 4, 5, 6, 7, 8};
        Assert.assertArrayEquals(expectArray, array3);
    }

    @Test
    public void testGrow() {
        int[] array = {2, 3, 6};
        int[] result = arrayUtil.grow(array, 3);
        Assert.assertEquals(result.length, 6);
        int[] expectedArray = {2, 3, 6, 0, 0, 0};
        Assert.assertArrayEquals(expectedArray, result);
    }

    @Test
    public void testFibonacci() {
        int[] result = arrayUtil.fibonacci(15);
        int[] expect = {1, 1, 2, 3, 5, 8, 13};
        Assert.assertArrayEquals(expect, result);
    }

    @Test
    public void testGetPerfectNumbers() {
        int[] result = arrayUtil.getPerfectNumbers(500);
        int[] expected = {6, 28, 496};
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testGetPrimes() {
        int[] result = arrayUtil.getPrimes(23);
        int[] expectedArray = {2, 3, 5, 7, 11, 13, 17, 19};
        Assert.assertArrayEquals(expectedArray, result);
    }

    @Test
    public void testJoin() {
        int[] source = {3,8,9};
        String result = arrayUtil.join(source, "-");
        Assert.assertEquals("3-8-9", result);
    }

}
