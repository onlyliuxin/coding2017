package structure.week2;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
    ArrayUtil u = null;
	@Before
	public void setUp() throws Exception {
		System.out.println("开始一个新的测试");
		u = new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testReverseArray() {
		int []array1 ={1,3,5,7,9,2,4,6,8,0};
		int[]array2 = {1,3,5,7,9,2,4,6,8};
		int []rarray1={0,8,6,4,2,9,7,5,3,1};
		int []rarray2={8,6,4,2,9,7,5,3,1};
		int []array3 ={7, 9, 30, 3, 4};
		int[]rarray3 = {4,3, 30 , 9,7};
		
		u.reverseArray(array1);
		Assert.assertArrayEquals(array1, rarray1);
		u.reverseArray(array2);
		Assert.assertArrayEquals(array2, rarray2);
		u.reverseArray(array3);
		Assert.assertArrayEquals(array3, rarray3);
	}

	@Test
	public void testRemoveZero() {
		int []test1={0,0,0,0,0,0};
		int[]rtest1=new int[0];
		int[]test2_0={0,0,0,1,2,3,4,5};
		int[]test2_1={1,2,3,4,5,0,0,0};
		int[]test2_2={0,1,0,0,2,0,0,0,3,0,4,0,5,0,0};
		int []rtest2={1,2,3,4,5};
		
		int []t = u.removeZero(test1);
		Assert.assertArrayEquals(rtest1,t);
		t = u.removeZero(test2_0);
		Assert.assertArrayEquals(rtest2,t);
		t = u.removeZero(test2_1);
		Assert.assertArrayEquals(rtest2,t);
		t = u.removeZero(test2_2);
		Assert.assertArrayEquals(rtest2,t);
	}

	@Test
	public void testMerge() {
		int []t=null,test1 = {1,3,5,7,9};
		int [] test1_0={2,4,6,8};
		int [] test1_1={1,2,2,3,3,3,4,4,4,4,6,8};
		int [] test1_2={1,2,3,4,5,6,7,8,9};
		int [] test1_3={};
		int []rtest = {1,2,3,4,5,6,7,8,9};
		
		t = u.merge(test1, test1_0);
		Assert.assertArrayEquals(rtest, t);
		t = u.merge(test1, test1_1);
		Assert.assertArrayEquals(rtest, t);
		t = u.merge(test1, test1_2);
		Assert.assertArrayEquals(rtest, t);
		t = u.merge(test1, test1_3);
		Assert.assertArrayEquals(test1, t);
	}

	@Test
	public void testGrow() {
		int []t=null,test1 = {1,3,5,7,9};
		int [] test1_0={1,3,5,7,9};
		int [] test1_1={1,3,5,7,9,0,0};
		
		t = u.grow(test1, 0);
		Assert.assertArrayEquals(test1_0, t);
		t = u.grow(test1, -2);
		Assert.assertArrayEquals(test1_0, t);
		t = u.grow(test1, 2);
		Assert.assertArrayEquals(test1_1, t);
	}

	@Test
	public void testFibonacci() {
		int [] t,test1 ={ 1,1,2,3,5,8,13,21};
		int [] test2 = {1,1,2,3,5,8,13,21,34,55,89};
		
		t = u.fibonacci(22);
		Assert.assertArrayEquals(test1, t);
		t = u.fibonacci(25);
		Assert.assertArrayEquals(test1, t);
		t = u.fibonacci(34);
		Assert.assertArrayEquals(test1, t);
		t = u.fibonacci(100);
		Assert.assertArrayEquals(test2, t);
	}

	@Test
	public void testGetPrimes() {
		int [] t,test1 ={2,3,5,7,11,13,17,19};
		int [] test2 = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
		
		t = u.getPrimes(20);
		Assert.assertArrayEquals(test1, t);
		t = u.getPrimes(23);
		Assert.assertArrayEquals(test1, t);
		t = u.getPrimes(50);
		Assert.assertArrayEquals(test2, t);
	}

	@Test
	public void testGetPerfectNumbers() {
		int []rtest1 = {6},rtest2={6,28},rtest3={6,28,496},t;
		
		t = u.getPerfectNumbers(7);
		Assert.assertArrayEquals(rtest1, t);
		t = u.getPerfectNumbers(29);
		Assert.assertArrayEquals(rtest2, t);
		t = u.getPerfectNumbers(500);
		Assert.assertArrayEquals(rtest3, t);
		
	}

	@Test
	public void testJoin() {
		String s,spec1="-",spec2="=";
		int[]test1= {1,2,3,4,5,6,7,9,8};
		String res1 = "1-2-3-4-5-6-7-9-8",res2="1=2=3=4=5=6=7=9=8";

		s = u.join(test1, spec1);
		Assert.assertEquals(res1, s);
		s = u.join(test1, spec2);
		Assert.assertEquals(res2, s);	
	}
}
