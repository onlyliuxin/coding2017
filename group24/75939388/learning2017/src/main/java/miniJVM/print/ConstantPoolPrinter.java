package miniJVM.print;


import miniJVM.constant.*;

public class ConstantPoolPrinter {
	ConstantPool pool;
	ConstantPoolPrinter(ConstantPool pool){
		this.pool = pool;
	}
	public void print(){
		System.out.println("Constant Pool:");
		int poolSize = pool.getSize();

		ConstantInfo.Visitor visitor = new ConstantInfo.Visitor() {
			public void visitClassInfo(ClassInfo info) {
				StringBuilder sb = new StringBuilder();
				sb.append("Class       ");
				sb.append("#" + info.getUtf8Index());
				sb.append("  //" + info.getClassName());
				System.out.println(sb.toString());
			}

			public void visitFieldRef(FieldRefInfo info) {
				StringBuilder sb = new StringBuilder();
				sb.append("Fieldref    ");
				sb.append("#" + info.getClassInfoIndex());
				sb.append("." + info.getNameAndTypeIndex());
				sb.append("  //" + info.getClassName());
				sb.append("." + info.getFieldName());
				sb.append(":" + info.getFieldType());
				System.out.println(sb.toString());
			}

			public void visitMethodRef(MethodRefInfo info) {
				StringBuilder sb = new StringBuilder();
				sb.append("MethodRef   ");
				sb.append("#" + info.getClassInfoIndex());
				sb.append(".").append("#" + info.getNameAndTypeIndex());
				sb.append("  //" + info.getClassName());
				sb.append("." + info.getMethodName());
				sb.append(":" + info.getParamAndReturnType());
				System.out.println(sb.toString());
			}

			public void visitNameAndType(NameAndTypeInfo info) {
				StringBuilder sb = new StringBuilder();
				sb.append("NameAndType ");
				sb.append("#" + info.getIndex1());
				sb.append(":#" + info.getIndex2());
				sb.append("  //" + info.getName());
				sb.append(":" + info.getTypeInfo());
				System.out.println(sb.toString());
			}

			public void visitString(StringInfo info) {
				StringBuilder sb = new StringBuilder();
				sb.append("String      ");
				sb.append("#" + info.getIndex());
				sb.append("  //" + info.toString());
				System.out.println(sb.toString());
			}

			public void visitUTF8(UTF8Info info) {
				StringBuilder sb = new StringBuilder();
				sb.append("Utf8        ");
				sb.append(info.getValue());
				System.out.println(sb.toString());
			}
		};

		for(int i = 1; i < poolSize; i++){
			System.out.print(i + "# = ");
			ConstantInfo cnst = pool.getConstantInfo(i);
			cnst.accept(visitor);
//			if(cnst instanceof ClassInfo){
//				sb.append("Class       ");
//				sb.append("#" + ((ClassInfo) cnst).getUtf8Index());
//				sb.append("  //" + ((ClassInfo) cnst).getClassName());
//			}else if(cnst instanceof UTF8Info){
//				sb.append("Utf8        ");
//				sb.append(((UTF8Info) cnst).getValue());
//			}else if(cnst instanceof MethodRefInfo){
//				sb.append("MethodRef   ");
//				sb.append("#" + ((MethodRefInfo) cnst).getClassInfoIndex());
//				sb.append(".").append("#" + ((MethodRefInfo) cnst).getNameAndTypeIndex());
//				sb.append("  //" + ((MethodRefInfo) cnst).getClassName());
//				sb.append("." + ((MethodRefInfo) cnst).getMethodName());
//				sb.append(":" + ((MethodRefInfo) cnst).getParamAndReturnType());
//			}else if(cnst instanceof NameAndTypeInfo){
//				sb.append("NameAndType ");
//				sb.append("#" + ((NameAndTypeInfo) cnst).getIndex1());
//				sb.append(":#" + ((NameAndTypeInfo) cnst).getIndex2());
//				sb.append("  //" + ((NameAndTypeInfo) cnst).getName());
//				sb.append(":" + ((NameAndTypeInfo) cnst).getTypeInfo());
//			}else if(cnst instanceof FieldRefInfo){
//				sb.append("Fieldref    ");
//				sb.append("#" + ((FieldRefInfo) cnst).getClassInfoIndex());
//				sb.append("." + ((FieldRefInfo) cnst).getNameAndTypeIndex());
//				sb.append("  //" + ((FieldRefInfo) cnst).getClassName());
//				sb.append("." + ((FieldRefInfo) cnst).getFieldName());
//				sb.append(":" + ((FieldRefInfo) cnst).getFieldType());
//			}else if(cnst instanceof StringInfo){
//				sb.append("String      ");
//				sb.append("#" + ((StringInfo) cnst).getIndex());
//				sb.append("  //" + cnst.toString());
//			}else{
//				throw new RuntimeException(cnst.getType() + "not processed");
//			}

//			System.out.println(sb.toString());
		}
	}
}
