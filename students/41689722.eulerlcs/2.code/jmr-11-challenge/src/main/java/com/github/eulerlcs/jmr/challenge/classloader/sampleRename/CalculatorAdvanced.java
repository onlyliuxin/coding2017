package com.github.eulerlcs.jmr.challenge.classloader.sampleRename;

import com.github.eulerlcs.jmr.challenge.classloader.core.ICalculator;

public class CalculatorAdvanced implements ICalculator {

	public String calculate(String expression) {
		return "Result is " + expression;
	}

	public String getVersion() {
		return "2.0";
	}

}
