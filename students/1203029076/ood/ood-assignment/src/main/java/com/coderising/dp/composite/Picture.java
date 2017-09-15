package com.coderising.dp.composite;

import java.util.ArrayList;

public class Picture implements Shape {
	ArrayList<Shape> shapes = new ArrayList<>();
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		for(Shape shape:shapes) {
			shape.draw();
		}
	}
	
	public void add(Shape shape) {
		shapes.add(shape);
	}
	
	public static void main(String[] args) {
		Picture aPicture = new Picture();
		
		Picture p = new Picture();
		p.add(new Text());
		p.add(new Line());
		p.add(new Rectangle());
		
		aPicture.add(p);
		
		aPicture.add(new Line());
		aPicture.add(new Rectangle());
		
		aPicture.draw();		
	}
}
