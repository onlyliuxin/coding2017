package com.github.orajavac.coding2017.ood.dp.bridge;

public class MyBridge extends Bridage{
	
	public void drawAGraph(){
		getG1().draw_a_circle(1,2,3);
		getG1().draw_a_line(1, 2,3,4);
	}
	
	public void drawGraph(){
		getG2().drawLine(1,2,3,4);
		getG2().drawCircle(1, 2,3);
	}	
}
