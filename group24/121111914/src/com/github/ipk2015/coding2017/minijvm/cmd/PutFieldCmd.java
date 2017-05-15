package com.github.ipk2015.coding2017.minijvm.cmd;

import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;
import com.github.ipk2015.coding2017.minijvm.constant.ClassInfo;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantInfo;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantPool;
import com.github.ipk2015.coding2017.minijvm.constant.FieldRefInfo;
import com.github.ipk2015.coding2017.minijvm.constant.NameAndTypeInfo;
import com.github.ipk2015.coding2017.minijvm.engine.ExecutionResult;
import com.github.ipk2015.coding2017.minijvm.engine.JavaObject;
import com.github.ipk2015.coding2017.minijvm.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd {

	public PutFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		FieldRefInfo fieldRefInfo = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		ClassInfo classInfo = (ClassInfo)this.getConstantInfo(fieldRefInfo.getClassInfoIndex());
		NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getConstantInfo(fieldRefInfo.getNameAndTypeIndex());
		// for example : name
		String fieldName = nameAndTypeInfo.getName();
		// for example : Ljava/lang/String : 注意：我们不再检查类型
		String fieldType = nameAndTypeInfo.getTypeInfo();
		JavaObject fieldValue = frame.getOprandStack().pop();
		JavaObject objectRef = frame.getOprandStack().pop();
		objectRef.setFieldValue(fieldName, fieldValue);
	}


}
