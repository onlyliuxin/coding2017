package com.coderising.ood.ocp;

public class Test {
	public static void main(String[] args) {
		Logger log = null;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				log = new Logger(i, j);
				log.log("test" + i + j);
			}
		}
	}
}
