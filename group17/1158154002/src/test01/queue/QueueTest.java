package test01.queue;

import org.junit.Test;

public class QueueTest{
	@Test
	public void test() {
		Queue<Integer> queueOne = new Queue<Integer>();
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
			System.out.println((Integer) data[i] == i);
		}

		for (int i = 0; i < removeCountOne; i++) {
			System.out.println(queueOne.remove() == i);
		}

		for (int i = 0; i < addCountTwo; i++) {
			queueOne.add(i * 10);
		}
		Object[] data2 = queueOne.getData();
		int baseCount = addCountOne - removeCountOne;
		for (int i = 0; i < addCountTwo; i++) {
			System.out.println((Integer) data2[baseCount + i] == i * 10);
		}
	}

	public static void main(String[] args) {
		new QueueTest().test();
	}
}
