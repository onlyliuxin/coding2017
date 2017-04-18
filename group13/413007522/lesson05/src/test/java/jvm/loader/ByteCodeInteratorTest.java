package jvm.loader;

import org.junit.Test;

import cn.xl.jvm.loader.ByteCodeIterator;

public class ByteCodeInteratorTest {

	@Test
	public void iter(){
		byte[] b = new byte[8];
		b[0] = 'C';
		b[1] = 'A';
		b[2] = 'F';
		b[3] = 'E';
		b[4] = 'B';
		b[5] = 'A';
		b[6] = 'B';
		b[7] = 'E';
		
		ByteCodeIterator bcit = new ByteCodeIterator(b);
		
		while(bcit.hasNext()){
			System.out.println(bcit.next());
		}
		
	}
}
