package com.coderising.ood.course.good;



public class CourseService {
	
	public void chooseCourse(Student student, CourseOffering sc){		
		sc.addStudent(student);
	}
}
