package cn.ecust.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.ecust.Array.ArrayUtil;

public class ArrayUtilTest {

	@Test
	public void test() {
		int[] a = {7, 9, 30, 0, 0, 0, 3, 4};
		int[] b = {3,4,5,6,7,8};
		ArrayUtil au = new ArrayUtil();
		au.reverseArray(a);
		//au.removeZero(a);
		au.merge(a, b);
		au.grow(a, 3);
		au.fibonacci(25);
		au.getPrimes(25);
		au.getPerfectNumbers(25);
		au.join(a, "-");
	}

}
