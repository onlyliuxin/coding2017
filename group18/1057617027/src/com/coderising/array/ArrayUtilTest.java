package com.coderising.array;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void joinTest() {
		ArrayUtil au = new ArrayUtil();
		int []  a = new int[3];
		a[0]=0;
		a[1]=1;
		a[2]=2;
		Assert.assertEquals("0~1~2", au.join(a,"~"));
	}

}
