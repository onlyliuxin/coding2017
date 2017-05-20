package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Priner implements Runnable{

	private static int taskcount ;
	
	private final int id = taskcount++;
	
	Priner(){
		System.out.println("Priner started ,ID= "+ id);
	}
	@Override
	public void run() {
		try {
			System.out.println("Stage 1... ID= "+id);
			//Thread.yield();
			TimeUnit.MILLISECONDS.sleep(1000);
			System.out.println("Stage 2... ID= "+id);
			//Thread.yield();
			TimeUnit.MILLISECONDS.sleep(1000);
			System.out.println("Stage 3... ID= "+id);
			//Thread.yield();
			TimeUnit.MILLISECONDS.sleep(1000);
			System.out.println("Priner end ,ID= "+id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

public class E01_Runnable{
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 3; i++)
			executor.execute(new Priner());
		
//		for (int i = 0; i < 3; i++){
//			new Thread(new Priner()).start();
//		}
	}
}