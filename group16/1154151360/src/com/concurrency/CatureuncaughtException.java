package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class  ExceptionThread2 implements Runnable{
	
	public void run(){
		Thread t = Thread.currentThread();
		System.out.println("run() by "+ t);
		System.out.println("eh= "+t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}
class MyuncaughtException implements Thread.UncaughtExceptionHandler{

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		System.out.println("caught "+e);
	}
	
}
class HandlerThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getSimpleName() + "  creating new Thread ");
		Thread t = new Thread(r);
		System.out.println("created "+ t);
		t.setUncaughtExceptionHandler(new MyuncaughtException());
		System.out.println("eh= "+ t.getUncaughtExceptionHandler());
		return t;
	}

}
public class CatureuncaughtException {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool(new HandlerThreadFactory());
		
		executor.execute(new ExceptionThread2());
	}
}
