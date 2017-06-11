package com.coderising.ood.course.good;

import java.util.List;

public class Course {
	private String id;
	private String desc;
	private int duration ;
	
	List<Course> prerequisites;
	
	public List<Course> getPrerequisites() {
		return prerequisites;
	}
	
}


