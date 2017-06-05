package weeks11.jvm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OverflowTest {

	Overflow of=null;
	
	@Before
	public void setUp(){
		of=new Overflow();
	}
	
	@Test
	public void testOutOfMemoryWithHeap(){
		of.outOfMemoryWithHeap();	
	}
	
	@Test
	public void teststackOverflowError(){
		of.stackOverflowError();
	}
	
	@Test
	public void testOutOfMemoryWithPermGen(){
		of.outOfMemoryWithPermGen();
	}
}
