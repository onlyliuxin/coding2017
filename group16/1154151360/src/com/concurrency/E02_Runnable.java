package com.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Fibonacci implements Runnable{

	private int n;
	
	public Fibonacci(int n) {
		this.n = n;
	}
	@Override
	public void run() {
		List<Integer>  fibonacci = new ArrayList<Integer>();
		
		if (n < 2)return ;
		
		fibonacci.add(1);
		fibonacci.add(1);
		
		for (int i = 2; i < n; i++){
			fibonacci.add(fibonacci.get(i - 1) + fibonacci.get(i - 2));
			Thread.yield();
		}
		
		System.out.println(fibonacci.toString());
	}
	
}
public class E02_Runnable {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 3; i <= 5; i++)
			executor.execute(new Fibonacci(i));
	}
}
