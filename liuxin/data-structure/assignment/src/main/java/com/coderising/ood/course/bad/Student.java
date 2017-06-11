package com.coderising.ood.course.bad;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String id;
	private String name;
	private List<Course> coursesAlreadyTaken = new ArrayList<Course>();
	
	public List<Course> getCoursesAlreadyTaken() {
		return coursesAlreadyTaken;
	}
}
