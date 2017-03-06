package increaseLearning.dataStructure;

import org.junit.Test;

import ListServiceImpl.KStackList;
import junit.framework.TestCase;

public class stackListTest extends TestCase{
	@Test
	public void testStackList(){
		KStackList<Integer> fcStack=new KStackList<Integer>();  
        for(int i=0;i<10;i++)  
        {  
            fcStack.push(i);  
            System.out.println(fcStack);
        }  
        
        for(int i=0;i<10;i++)  
        {  
            fcStack.pop();  
            System.out.println(fcStack);
        }  
        fcStack.pop(); 
	}

}
