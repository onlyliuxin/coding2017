package org.litejunit.v3.internal.runners;

import org.litejunit.v3.runner.Description;
import org.litejunit.v3.runner.Runner;
import org.litejunit.v3.runner.manipulation.*;
import org.litejunit.v3.runner.notification.RunNotifier;

import java.util.*;

public class CompositeRunner extends Runner implements Filterable, Sortable {
	private final List<Runner> fRunners= new ArrayList<Runner>();
	private final String fName;
	
	public CompositeRunner(String name) {
		fName= name;
	}
	
	@Override
	public void run(RunNotifier notifier) {
		for (Runner each : fRunners)
			each.run(notifier);
	}

	@Override
	public Description getDescription() {
		Description spec= Description.createSuiteDescription(fName);
		for (Runner runner : fRunners) {
			spec.addChild(runner.getDescription());
		}
		return spec;
	}

	public List<Runner> getRunners() {
		return fRunners;
	}

	public void addAll(List<? extends Runner> runners) {
		fRunners.addAll(runners);
	}

	public void add(Runner runner) {
		fRunners.add(runner);
	}
	
	public void filter(Filter filter) throws NoTestsRemainException {
		for (Iterator iter= fRunners.iterator(); iter.hasNext();) {
			Runner runner= (Runner) iter.next();
			if (filter.shouldRun(runner.getDescription())) {
				filter.apply(runner);
			} else {
				iter.remove();
			}
		}
	}

	protected String getName() {
		return fName;
	}

	public void sort(final Sorter sorter) {
		Collections.sort(fRunners, new Comparator<Runner>() {
			public int compare(Runner o1, Runner o2) {
				return sorter.compare(o1.getDescription(), o2.getDescription());
			}
		});
		for (Runner each : fRunners) {
			sorter.apply(each);
		}
	}
}
