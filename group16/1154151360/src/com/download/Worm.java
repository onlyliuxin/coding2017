package com.download;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

 class Data implements Serializable{
	 
	 private int n;
	 public Data(int n){ this.n = n;}
	 public String toString() {return Integer.toString(n);}
 }
public class Worm {

	private static Random random = new Random(47);
	
	private Data [] d = {new Data( random.nextInt(10)),new Data(random.nextInt(10))};
	
	private Worm next;
	
	private char c;
	
	public Worm(int i, char x){
		System.out.print("Worm constructor: "+ i);
		c = x;
		if (--i > 0)
			next = new Worm(i, (char)(x + 1));
	}
	
	public Worm(){
		System.out.println("Default constructor");
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder(":");
		result.append(c)
			  .append("(");
		for (Data dat: d){
			result.append(dat);
		}
		result.append(")");
		if (next != null)
			result.append(next);
		return result.toString();
	}
	
	public static void main(String[] args) {
		
		Worm w = new Worm(4,'a');
		System.out.println("w= "+ w);
		
		
		
	}
}
