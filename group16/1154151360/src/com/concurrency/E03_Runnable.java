package com.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class Fibonacci2 implements Callable<String>{

	private int n;
	
	public Fibonacci2(int n) {
		this.n = n;
	}
	
	@Override
	public String call() throws Exception {
		List<Integer>  fibonacci = new ArrayList<Integer>();
		
		if (n < 2)return "";
		
		fibonacci.add(1);
		fibonacci.add(1);
		
		for (int i = 2; i < n; i++){
			fibonacci.add(fibonacci.get(i - 1) + fibonacci.get(i - 2));
			TimeUnit.MILLISECONDS.sleep(5000);
		}
		return fibonacci.toString();
	}
	
	
}
public class E03_Runnable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		Future<String> future = executor.submit(new Fibonacci2(5));
		
		System.out.println(future.get()+ " "+future.isDone());
		System.out.println();
	}
}
