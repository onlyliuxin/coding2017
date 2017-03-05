package increaseLearning.dataStructure;

import org.junit.Assert;
import org.junit.Test;

import ListServiceImpl.KQueueList;
import junit.framework.TestCase;

public class queueListTest extends TestCase {
	@Test
	public void testQueueList() {
		KQueueList<Integer> queueOne = new KQueueList<Integer>();
		// 第1次 加入个数
		int addCountOne = 30;
		// 第1次 删除个数
		int removeCountOne = 20;
		// 第2次 加入个数
		int addCountTwo = 10;

		for (int i = 0; i < addCountOne; i++) {
			queueOne.add(i);
		}
		Object[] data = queueOne.getData();
		for (int i = 0; i < data.length; i++) {
			Assert.assertTrue((Integer) data[i] == i);
		}

		for (int i = 0; i < removeCountOne; i++) {
			Assert.assertTrue(queueOne.remove() == i);
		}

		for (int i = 0; i < addCountTwo; i++) {
			queueOne.add(i * 10);
		}
		Object[] data2 = queueOne.getData();
		int baseCount = addCountOne - removeCountOne;
		for (int i = 0; i < addCountTwo; i++) {
			Assert.assertTrue((Integer) data2[baseCount + i] == i * 10);
		}
	}

}
