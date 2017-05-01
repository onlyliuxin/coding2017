package com.download;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


 class House implements Serializable{}
 
 class Animal implements Serializable{
	 private String name;
	 private House preferredHouse;
	 Animal(String nm, House h){
		 name = nm;
		 preferredHouse = h;
	 }
	 
	 public String toString(){
		 return name + "[ " +super.toString()+" ], "+preferredHouse+"\n"; 
	 }
 }
 
public class MyWorld {

	public static void main(String[] args) throws Exception{
		
		House house = new House();
		
		List<Animal> animals = new ArrayList<Animal>();
		
		animals.add(new Animal("Bosco the dog", house));
		animals.add(new Animal("Molly the cat", house));
		
		System.out.println("animals" + animals);
		
		ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
		
		ObjectOutputStream o1 = new ObjectOutputStream(buf1);
		
		o1.writeObject(animals);
		
		o1.writeObject(animals);
		
		ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
		
		List animals1 = (List) in1.readObject();
		List animals2 = (List) in1.readObject();
		
		System.out.println("animals1" + animals1);
		System.out.println("animals2" + animals2);
	}
}
