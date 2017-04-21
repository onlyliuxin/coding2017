package print;

import constant.*;

/**
 * Created by gongxun on 2017/4/21.
 */
public class ConstantPoolPrinter {
    ConstantPool pool;
    ConstantPoolPrinter(ConstantPool pool){
        this.pool = pool;
    }
    public void print(){
        System.out.println("Constant Pool:");

        ConstantInfo.Visitor visitor = new ConstantInfo.Visitor() {
            @Override
            public void visitClassInfo(ClassInfo info) {

            }

            @Override
            public void visitFieldRef(FieldRefInfo info) {

            }

            @Override
            public void visitMethodRef(MethodRefInfo info) {

            }

            @Override
            public void visitNameAndType(NameAndTypeInfo info) {

            }

            @Override
            public void visitString(StringInfo info) {

            }

            @Override
            public void visistUTF8(UTF8Info info) {

            }
        };
    }
}
