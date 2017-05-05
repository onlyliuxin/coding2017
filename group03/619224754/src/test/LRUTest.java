package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coderising.lru.LRU;

public class LRUTest {

	@Test
	public void test() {

      Integer iter[] = {4,7,0,7,1,0,1,2,1,2,6};  
        LRU lru = new LRU();  
        for(int i=0; i<iter.length; i++) {  
            lru.push(iter[i]);  
            lru.showMemoryBlock();  
            System.out.println();  
        }  
	}

}
