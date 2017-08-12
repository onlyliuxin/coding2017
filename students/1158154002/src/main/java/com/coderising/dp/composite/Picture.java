package com.coderising.dp.composite;

import java.util.ArrayList;
import java.util.List;

public class Picture implements Shape{
	private List<Shape> shapes=new ArrayList<>();
	@Override
	public void draw() {
		for (Shape shape : shapes) {
			shape.draw();
		}
	}
	
	public void add(Shape shape){
		shapes.add(shape);
	}
	
	public static void main(String[] args) {
		Picture aPicture=new Picture();
		aPicture.add(new Line());
		aPicture.add(new Rectangle());
		
		Picture a=new Picture();
		a.add(new Text());
		a.add(new Line());
		a.add(new Square());
		aPicture.add(a);
		
		aPicture.draw();
	}

}
