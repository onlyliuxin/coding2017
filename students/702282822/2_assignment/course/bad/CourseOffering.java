package com.coderising.ood.course.bad;

import java.util.ArrayList;
import java.util.List;


public class CourseOffering {
	private Course course;
	private String location;
	private String teacher;
	private int maxStudents;
	
	List<Student> students = new ArrayList<Student>();	
	
	public void addStudent(Student st)
	{
		if(st.canAttend(course) && maxStudents > students.size())
		{
			students.add(st);
		}
	}
	
	
}
