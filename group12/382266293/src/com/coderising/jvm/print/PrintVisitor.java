package com.coderising.jvm.print;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;

public interface PrintVisitor {
	
	void visit(ClassInfo pool);
	void visit(FieldRefInfo info);
	void visit(MethodRefInfo info);
	void visit(NameAndTypeInfo info);
	void visit(StringInfo info);
	void visit(UTF8Info info);
	void visit(NullConstantInfo nullConstantInfo);

}
