package com.coding.basic;

import java.lang.reflect.Method;

public class Heros {
    private String name;//名字
    private String type;//类型
    private int camp;//0,近卫；1，天灾
	public Heros(){
    	
    }
 /*
    public Heros(String name, String type, int camp) {
    	
        super();
      
        this.name = name;
        this.type = type;
        this.camp = camp;
    }*/
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    public int getCamp() {
        return camp;
    }
 
    public void setCamp(int camp) {
        this.camp = camp;
    }
 
    @Override
    public String toString() {
        return "Heros [\n name=" + name + ", \n type=" + type + ", \n camp=" + camp + "\n]";
    }
     
}