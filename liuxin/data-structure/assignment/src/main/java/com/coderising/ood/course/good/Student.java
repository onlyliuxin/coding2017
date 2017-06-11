package com.coderising.ood.course.good;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String id;
	private String name;
	private List<Course> coursesAlreadyTaken = new ArrayList<Course>();
	
	public List<Course> getCoursesAlreadyTaken() {
		return coursesAlreadyTaken;
	}	
	
	public boolean canAttend(Course course){
		return this.coursesAlreadyTaken.containsAll(
				course.getPrerequisites());
	}
}


