package com.github.eulerlcs.jmr.challenge.camel;

import org.apache.camel.main.Main;

public class Hello1 {
	public static void main(String[] args) throws Exception {
		System.out.println("hello camel");

		Main main = new Main();
		main.start();
	}
}
