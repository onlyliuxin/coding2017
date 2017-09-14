/**
 * 
 */
package org.litejunit.v3.internal.requests;


import org.litejunit.v3.internal.runners.CompositeRunner;
import org.litejunit.v3.runner.Request;
import org.litejunit.v3.runner.Runner;

public class ClassesRequest extends Request {
	private final Class[] fClasses;
	private final String fName;
	
	public ClassesRequest(String name, Class... classes) {
		fClasses= classes;
		fName= name;
	}

	@Override
	public Runner getRunner() {
		CompositeRunner runner= new CompositeRunner(fName);
		for (Class<?> each : fClasses) {
			Runner childRunner= Request.aClass(each).getRunner();
			if (childRunner != null)
				runner.add(childRunner);
		}
		return runner;
	}
}