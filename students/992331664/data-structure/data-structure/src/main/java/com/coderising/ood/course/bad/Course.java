package com.coderising.ood.course.bad;

import java.util.List;

public class Course {
	private String id;
	private String desc;
	private int duration ;
	
	List<Course> prerequisites;
	
	public List<Course> getPrerequisites() {
		return prerequisites;
	}
	
	
	public boolean equals(Object o){
		if(o == null || !(o instanceof Course)){
			return false;
		}
		Course c = (Course)o;
		return (c != null) && c.id.equals(id);
	}
}
