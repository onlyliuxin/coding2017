package com.coderising.dp.composite;

import java.util.ArrayList;
import java.util.List;

import org.junit.validator.PublicClassValidator;

public class Picture implements Shape {
	List<Shape> list = new ArrayList<>();

	public void addList(Shape shape){
		list.add(shape);
	}
	
	@Override
	public void draw() {
		System.out.println("Picture:");
		for (Shape shape : list) {
			shape.draw();
		}
	}

}
