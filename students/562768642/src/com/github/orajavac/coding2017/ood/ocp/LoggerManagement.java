package com.github.orajavac.coding2017.ood.ocp;

import java.util.ArrayList;
import java.util.List;

public class LoggerManagement {

	public void log(String msg){
		
		List<RawLogger> rawlog = new ArrayList<RawLogger>();
		for (RawLogger r : rawlog){
			r.log(msg);
		}
		
		List<Logger> logger = new ArrayList<Logger>();
		for (Logger l : logger){
			l.send(msg);
		}
	}
}
