package week8.jvm.print;

import week8.jvm.constant.ClassInfo;
import week8.jvm.constant.ConstantInfo;
import week8.jvm.constant.ConstantPool;
import week8.jvm.constant.FieldRefInfo;
import week8.jvm.constant.MethodRefInfo;
import week8.jvm.constant.NameAndTypeInfo;
import week8.jvm.constant.StringInfo;
import week8.jvm.constant.UTF8Info;

public class ConstantPoolPrinter {

	ConstantPool pool;
	
	public ConstantPoolPrinter(ConstantPool pool){
		this.pool=pool;
	}
	
	public void print(){
		
		ConstantInfo.Visitor visitor=new ConstantInfo.Visitor() {
			
			@Override
			public void visitUTF8(UTF8Info info) {
				StringBuffer buffer=new StringBuffer();
				buffer.append("UTF8   ").append(info.getValue());
				System.out.println(buffer);
			}
			
			@Override
			public void visitString(StringInfo info) {
				StringBuffer buffer=new StringBuffer();
				buffer.append("String    #").append(info.getIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitNameAndType(NameAndTypeInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("NameAndType    #").append(info.getNameIndex()).append(":#")
				.append(info.getTypeIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitMethodRef(MethodRefInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("MethodRef    #").append(info.getClassInfoIndex()).append(".#")
				.append(info.getNameAndTypeIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitFieldRef(FieldRefInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("FieldRef    #").append(info.getClassInfoIndex()).append(".#")
				.append(info.getNameAndTypeIndex());
				System.out.println(buffer);
				
			}
			
			@Override
			public void visitClassInfo(ClassInfo info) {
				StringBuilder buffer = new StringBuilder();
				buffer.append("Class    #").append(info.getUtf8Index())
				.append("  ").append(info.getClassName());
				
				System.out.println(buffer);
			}
		};
		
		for(int i=1;i<=pool.getSize();i++){
			ConstantInfo info=pool.getConstantInfo(i);
			System.out.print("#"+i+"=");
			info.accept(visitor);
		}
	}
}
