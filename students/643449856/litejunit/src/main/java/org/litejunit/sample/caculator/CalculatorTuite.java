package org.litejunit.sample.caculator;

import org.litejunit.Test;
import org.litejunit.TestTuite;

public class CalculatorTuite {
public static Test tuite() {
	TestTuite tuite=new TestTuite("CalculatorTuite");
	tuite.addTestTuite(CaculatorTestCase.class);
	 return tuite;
}
}
