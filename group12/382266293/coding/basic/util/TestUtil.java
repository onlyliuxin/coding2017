package util;

import java.util.Random;

import collection.List;
import collection.concrete.ArrayList;
import collection.concrete.LinkedList;
import collection.concrete.Queue;
import junit.framework.TestCase;

public class TestUtil<E> extends TestCase {

	private static Random random = new Random();
	private static final int RANDOM_BOUND = 2 << 15;
	private static final int RANDOM_SIZE = 500;

	public static int[] getRandomIntArray(int number) {
		int[] arr = new int[number];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = getRandomNumber() + 1;
		}
		return arr;
	}

	public static int getRandomNumber() {
		return random.nextInt(RANDOM_SIZE);
	}

	public static int getRandomNumber(int bound) {
		return random.nextInt(bound);
	}

	public static void addIntWithNatureOrder(List<Integer> myList, int numbers) {

		for (int acutal = 0; acutal < numbers; acutal++) {
			myList.add(acutal);
		}
	}

	public static int[] addRandomInt(List<Integer> myList, int numbers) {

		int actual = 0;
		int[] result = new int[numbers];
		for (int i = 0; i < numbers; i++) {
			actual = random.nextInt(RANDOM_BOUND);
			result[i] = actual;
			myList.add(actual);
		}
		return result;
	}

	public static void addString(List<String> myList, int num) {

		String actual;
		for (int index = 0; index < num; index++) {
			actual = index + "";
			myList.add(actual);
		}
	}

	public static <E> void testRemoveAndGetFromTail(ArrayList<E> myList) {
		E get;
		E remove;
		for (int i = myList.size() - 1; i >= 0; i--) {
			get = myList.get(i);
			remove = myList.remove(i);
			assertEquals(get, remove);
		}
	}

	public static <E> void testRemoveAndGetFromTail(LinkedList<E> myList) {
		E get;
		E remove;
		for (int i = myList.size() - 1; i >= 0; i--) {
			get = myList.get(i);
			remove = myList.remove(i);
			assertEquals(get, remove);
		}
	}

	public static void enQueueIntWithNatureOrder(Queue<Integer> myQueue, int numbers) {

		for (int acutal = 0; acutal < numbers; acutal++) {
			myQueue.enQueue(acutal);
		}
	}

}
