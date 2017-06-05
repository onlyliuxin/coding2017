package com.coderising.jvm.print;

import java.util.Arrays;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.BiPushCmd;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.cmd.GetFieldCmd;
import com.coderising.jvm.cmd.GetStaticFieldCmd;
import com.coderising.jvm.cmd.InvokeSpecialCmd;
import com.coderising.jvm.cmd.InvokeVirtualCmd;
import com.coderising.jvm.cmd.LdcCmd;
import com.coderising.jvm.cmd.NewObjectCmd;
import com.coderising.jvm.cmd.NoOperandCmd;
import com.coderising.jvm.cmd.OneOperandCmd;
import com.coderising.jvm.cmd.PutFieldCmd;
import com.coderising.jvm.cmd.TwoOperandCmd;
import com.coderising.jvm.method.Method;

public class CommandPrinterJavaP implements CommandPrinter{

	ClassFile clzFile = null;
	String space_1 = "%-5s";
	String space_2 = "%-20s";
	String space_3 = "%-20s";
	String space_4 = "%-20s";
	
	public CommandPrinterJavaP(ClassFile clzFile){
		this.clzFile = clzFile;
	}
	
	public void print(){
//		clzFile.getMethods().forEach(method ->{
//			//System.out.println("Print Method Code: " + method.getCodeAttr().getCode());
//			Arrays.asList(method.getCmds()).forEach(cmd->{
//				cmd.print(this);
//				//System.out.println();
//			});
//		});
		System.out.println();
		for(Method method : clzFile.getMethods()){
			System.out.println("Dump Code: "+method.getCodeAttr().getCode());
			for(ByteCodeCommand bcc: method.getCmds()){
				
				bcc.print(this);
			}
		}
	}
	
	@Override
	public void printBiPushCmd(BiPushCmd cmd) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(space_1, cmd.getOpCode()));
		sb.append(String.format(space_2, cmd.getReadableCodeText()));
		sb.append(String.format(space_3, cmd.getOperand()));
		
		System.out.println(sb.toString());
		
	}

	@Override
	public void printGetFieldCmd(GetFieldCmd cmd) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(space_1, cmd.getOpCode()));
		sb.append(String.format(space_2, cmd.getReadableCodeText()));
		sb.append(String.format(space_3, cmd.getIndex()));
		sb.append(String.format(space_4, clzFile.getConstantPool().getConstantInfo(cmd.getIndex())));
		
		System.out.println(sb.toString());
	}

	@Override
	public void printGetStaticFieldCmd(GetStaticFieldCmd cmd) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(space_1, cmd.getOpCode()));
		sb.append(String.format(space_2, cmd.getReadableCodeText()));
		sb.append(String.format(space_3, cmd.getIndex()));
		sb.append(String.format(space_4, clzFile.getConstantPool().getConstantInfo(cmd.getIndex())));

		
		System.out.println(sb.toString());
	}

	@Override
	public void printInvokeSpecialCmd(InvokeSpecialCmd cmd) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(space_1, cmd.getOpCode()));
		sb.append(String.format(space_2, cmd.getReadableCodeText()));
		sb.append(String.format(space_3, cmd.getIndex()));
		sb.append(String.format(space_4, clzFile.getConstantPool().getConstantInfo(cmd.getIndex())));

		System.out.println(sb.toString());
	}

	@Override
	public void printInvokeVirtualCmd(InvokeVirtualCmd cmd) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(space_1, cmd.getOpCode()));
		sb.append(String.format(space_2, cmd.getReadableCodeText()));
		sb.append(String.format(space_3, cmd.getIndex()));
		sb.append(String.format(space_4, clzFile.getConstantPool().getConstantInfo(cmd.getIndex())));

		System.out.println(sb.toString());		
	}

	@Override
	public void printLdcCmd(LdcCmd cmd) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(space_1, cmd.getOpCode()));
		sb.append(String.format(space_2, cmd.getReadableCodeText()));
		sb.append(String.format(space_3, cmd.getOperand()));
		System.out.println(sb.toString());
	}

	@Override
	public void printNewObjectCmd(NewObjectCmd cmd) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(space_1, cmd.getOpCode()));
		sb.append(String.format(space_2, cmd.getReadableCodeText()));
		sb.append(String.format(space_3, cmd.getIndex()));
		sb.append(String.format(space_4, clzFile.getConstantPool().getConstantInfo(cmd.getIndex())));

		System.out.println(sb.toString());
		
	}

	@Override
	public void printPutFieldCmd(PutFieldCmd cmd) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(space_1, cmd.getOpCode()));
		sb.append(String.format(space_2, cmd.getReadableCodeText()));
		sb.append(String.format(space_3, cmd.getIndex()));
		sb.append(String.format(space_4, clzFile.getConstantPool().getConstantInfo(cmd.getIndex())));

		System.out.println(sb.toString());
		
	}

	@Override
	public void printNoOperandCmd(NoOperandCmd cmd) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format(space_1, cmd.getOpCode()));
		sb.append(String.format(space_2, cmd.getReadableCodeText()));
		System.out.println(sb.toString());
		
	}

	@Override
	public void printOneOperandCmd(OneOperandCmd cmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printTwoOperandCmd(TwoOperandCmd cmd) {
		// TODO Auto-generated method stub
		
	}

}
