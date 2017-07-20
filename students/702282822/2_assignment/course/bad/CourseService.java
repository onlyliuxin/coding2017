package com.coderising.ood.course.bad;



public class CourseService {
	
	public void chooseCourse(Student student, CourseOffering sc){		
		//如果学生上过该科目的先修科目，并且该课程还未满， 则学生可以加入该课程		
		sc.addStudent(student);			
	}
	
}
