package com.coding.basic.stack.expr.util;

public enum Operator {
	
	ADD("+",1),SUB("-",1),MULTY("*",2),DIVIDE("/",2)
	,LEFTBRACKRT("(",3),RIGHTBRACKET(")",3);
	
	private String flag;
	private int level;
	
	private Operator(String flag,int level){
		this.flag = flag;
		this.level = level;
	}

	public static int getLevelByFlag(String flag){
		
		Operator[] opers = Operator.values();
		for (Operator operator : opers) {
			if(operator.flag.equals(flag)){
				return operator.level;
			}
		}
		return -1;
	}
	
    public static Operator getOperator(String flag){
		
    	if(ADD.flag.equals(flag)){
    		return ADD;
    	}else if(SUB.flag.equals(flag)){
    		return SUB;
    	}else if(MULTY.flag.equals(flag)){
    		return MULTY;
    	}else if(DIVIDE.flag.equals(flag)){
    		return DIVIDE;
    	}else if(LEFTBRACKRT.flag.equals(flag)){
    		return LEFTBRACKRT;
    	}else if(RIGHTBRACKET.flag.equals(flag)){
    		return RIGHTBRACKET;
    	}
    	return null;
	}
    
    public static boolean contains(String flag){
    	
    	if(ADD.flag.equals(flag)){
    		return true;
    	}else if(SUB.flag.equals(flag)){
    		return true;
    	}else if(MULTY.flag.equals(flag)){
    		return true;
    	}else if(DIVIDE.flag.equals(flag)){
    		return true;
    	}else if(LEFTBRACKRT.flag.equals(flag)){
    		return true;
    	}else if(RIGHTBRACKET.flag.equals(flag)){
    		return true;
    	}
    	return false;
    }
	public String getFlag() {
		return flag;
	}

	public int getLevel() {
		return level;
	}
	
}
