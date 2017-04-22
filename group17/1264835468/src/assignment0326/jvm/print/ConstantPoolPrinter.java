package assignment0326.jvm.print;


import assignment0326.jvm.constant.*;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class ConstantPoolPrinter {
	private int indexLength;
	ConstantPool pool;
	private final String pattern;
	private List<String> comments = new ArrayList<>();
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
		indexLength = getMaxIndexLength() + 3;
		pattern = "%" + indexLength + "s";
		comments.add("nullConstant");
		generateComments();
	}

	public void print(){

		System.out.println("Constant Pool:");

		for (int i = 1; i < pool.getSize(); i++) {
			System.out.print(new Formatter().format(pattern, "#" + i) + " = "+new Formatter().format("%-20s",pool.getConstantInfo(i).typeDescription()));
			System.out.print(new Formatter().format("%-20s",pool.getConstantInfo(i).contentDescription()));
			if(!(pool.getConstantInfo(i) instanceof UTF8Info))
				System.out.print("// "+getComment(i));
			System.out.println();
		}

	}

	private int getMaxIndexLength() {
		return String.valueOf(pool.getSize()).length();
	}

	private void generateComments() {
		for (int i = 1; i < pool.getSize(); i++) {
			comments.add("null");
		}
		for (int i = 1; i < pool.getSize(); i++) {
			if(pool.getConstantInfo(i).getType()== ConstantInfo.UTF8_INFO){
				comments.set(i,pool.getConstantInfo(i).contentDescription());
			}
		}
		while (comments.contains("null")) {
			for (int i = 1; i < pool.getSize(); i++) {
				if(!getComment(i).equals("null"))
					continue;
				ConstantInfo constantInfo = pool.getConstantInfo(i);

				if (constantInfo.getType() == ConstantInfo.CLASS_INFO) {
					ClassInfo classInfo = (ClassInfo) constantInfo;
					comments.set(i, getComment(classInfo.getUtf8Index()));
				}
				if (constantInfo.getType() == ConstantInfo.STRING_INFO) {
					StringInfo stringInfo = (StringInfo) constantInfo;
					comments.set(i, getComment(stringInfo.getIndex()));
				}
				if (constantInfo.getType() == ConstantInfo.FIELD_INFO) {
					FieldRefInfo fieldRefInfo = (FieldRefInfo) constantInfo;
					comments.set(i, getComment(fieldRefInfo.getClassInfoIndex(),fieldRefInfo.getNameAndTypeIndex()));
				}
				if (constantInfo.getType() == ConstantInfo.METHOD_INFO) {
					MethodRefInfo methodRefInfo = (MethodRefInfo) constantInfo;
					comments.set(i, getComment(methodRefInfo.getClassInfoIndex(),methodRefInfo.getNameAndTypeIndex()));
				}
				if (constantInfo.getType() == ConstantInfo.NAME_AND_TYPE_INFO) {
					NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) constantInfo;
					comments.set(i, getComment(nameAndTypeInfo.getIndex1(),nameAndTypeInfo.getIndex2()));
				}
			}
		}
	}

	private String getComment(int index) {
		return comments.get(index);
	}
	private String getComment(int index1,int index2){
		String s1 = getComment(index1);
		String s2 = getComment(index2);
		if (s1.equals("null") || s2.equals("null")) {
			return "null";
		}
		return s1 + "." + s2;
	}

}
