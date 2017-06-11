package com.coderising.ood.course.good;



public class CourseService {
	
	public void chooseCourse(Student student, CourseOffering sc){		
		//第一步：重构： canAttend ， 但是还有问题
		if(student.canAttend(sc.getCourse())
				&& sc.getMaxStudents() > sc.getStudents().size()){
			sc.getStudents().add(student);
		}
	}
}
