package increaseLearning.dataStructure;

import org.junit.Test;

import ListServiceImpl.KLinkedList;
import junit.framework.TestCase;

public class linkedListTest extends TestCase{
	@Test
	public void testLinkedList(){
		KLinkedList<Integer> linkList=new KLinkedList<Integer>();
		
		
		linkList.add(3, 0);
		
		System.out.println(linkList.get(0));
//		System.out.println("成功！！！");
		
	}

}
