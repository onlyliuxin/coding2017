package nishuibaichuan.homework.first;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/** 
 * @Desc: ()
 * @date: 2017年3月12日 下午7:00:22  
 * @email:2411685663@qq.com 
 */
@SuppressWarnings("unused")
public class TestArrayList {

	private ArrayList arrayList = new ArrayList();
	
	@Test
	public void testAll() {
		arrayList.add(0);
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(4);
		arrayList.add(5);
		arrayList.add(6);
		
		Assert.assertEquals(arrayList.size(), 7);
		Assert.assertEquals(arrayList.get(1), 1);
		System.out.println(arrayList.get(3));
		
		arrayList.add(0, 100);
		Assert.assertEquals(arrayList.size(), 8);
		Assert.assertEquals(arrayList.get(0), 100);
		
		arrayList.remove(0);
		Assert.assertEquals(arrayList.size(), 7);
		Assert.assertEquals(arrayList.get(0), 0);
		
		Iterator iterator = arrayList.iterator();
		while(iterator.hasNext()){
			iterator.next();
			iterator.remove();
		}
		Assert.assertEquals(arrayList.size(), 0);
	}

}
