package com.coderising.ood.course.bad;

import java.util.ArrayList;
import java.util.List;


public class CourseOffering {
	private Course course;
	private String location;
	private String teacher;
	private int maxStudents;
	
	List<Student> students = new ArrayList<Student>();
	
	public int getMaxStudents() {
		return maxStudents;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public Course getCourse() {
		return course;
	}	
}
