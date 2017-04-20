package com.coderising.jvm.print;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public class ConstantPoolPrinter {
	
	ConstantPool pool;
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}
	public void print(){
		
		System.out.println("Constant Pool:");
		
		ConstantInfo.Visitor visitor = new ConstantInfo.Visitor() {
			
			@Override
			public void visitString(StringInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append(printConsName("string")).append("#"+info.getIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitNameAndType(NameAndTypeInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append(printConsName("NameAndType")).append("#"+info.getIndex1()).append(":#")
				.append(info.getIndex2());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitMethodRef(MethodRefInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append(printConsName("MethodRef")).append("#"+info.getClassInfoIndex()).append(".#")
				.append(info.getNameAndTypeIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitFieldRef(FieldRefInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append(printConsName("FieldRef")).append("#"+info.getClassInfoIndex()).append(".#")
				.append(info.getNameAndTypeIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitClassInfo(ClassInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append(printConsName("Class")).append("#"+info.getUtf8Index())
				.append("  ").append(info.getClassName());
				
				System.out.println(buffer);
				
			}
			
			@Override
			public void visistUTF8(UTF8Info info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append(printConsName("UTF8")).append(info.getValue());
				System.out.println(buffer);
				
			}
		};
		
		int size = pool.getSize();		
		for(int i=1; i<=size; i++){
			ConstantInfo constantInfo = pool.getConstantInfo(i);
			String space = genaralSpace(size,i);
			System.out.print(space+"#"+i+"=");
			constantInfo.accept(visitor);			
		}
	}
	
	private String genaralSpace(int size,int i){
		
		int s1 = String.valueOf(size).length();//数字的位数
		int s2 = String.valueOf(i).length();//数字的位数
		StringBuffer str = new StringBuffer();
		for (int j = 0; j < s1-s2; j++) {
			str.append(" ");
		}
		return str.toString();
	}
	
	/**
	 * 输出常量名称后面的空格
	 * @param consBeforStr
	 */
	private String printConsName(String consName){
		
		String bashStr = "NameAndType";		
		int bashLen = bashStr.length();
		int offset = bashLen-consName.length();
		for (int i = 0; i < offset; i++) {
			consName+= " ";
		}
		return consName+"     ";
	}
}
