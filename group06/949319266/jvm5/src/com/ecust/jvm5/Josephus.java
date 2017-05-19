package com.ecust.jvm5;

import java.util.ArrayDeque;

import java.util.ArrayList;

import java.util.List;



public class Josephus {





	public static List<Integer> execute(int n, int m){

		Preconditions.checkArgument(n > 0);

		ArrayList<Integer> deadList = new ArrayList<>();

		ArrayDeque<Integer> deque1 = new ArrayDeque<>();

		ArrayDeque<Integer> deque2 = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {

			deque1.offer(i);

		}

		int count = 0;

		while(n >= m){

			if (deque1.isEmpty()) {

				while (!deque2.isEmpty()){

					deque1.offer(deque2.poll());

				}

			}

			count++;

			if (count == m) {

				deadList.add(deque1.poll());

				count = 0;

				n--;

				continue;

			}

			deque2.offer(deque1.poll());



		}



		return deadList;

	}





}