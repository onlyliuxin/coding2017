package com.coderising.ood.course.good;

import java.util.ArrayList;
import java.util.List;

public class CourseOffering {
	private Course course;
	private String location;
	private String teacher;
	private int maxStudents;
	
	List<Student> students = new ArrayList<Student>();
	
	public List<Student> getStudents() {
		return students;
	}
	public int getMaxStudents() {
		return maxStudents;
	}
	public Course getCourse() {
		return course;
	}
	
	
	// 第二步：　把主要逻辑移动到CourseOffering 中
	public void addStudent(Student student){
		
		if(student.canAttend(course) 
				&& this.maxStudents > students.size()){
			students.add(student);
		}
	}
	// 第三步： 重构CourseService
}
