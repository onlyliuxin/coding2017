package me.lzb.jvm.print;

import me.lzb.jvm.cmd.*;

/**
 * Created by LZB on 2017/5/1.
 */
public interface ExecutionVisitor {

    void visitBiPushCmd(BiPushCmd cmd);

    void visitGetFieldCmd(GetFieldCmd cmd);

    void visitGetStaticFieldCmd(GetStaticFieldCmd cmd);

    void visitInvokeSpecialCmd(InvokeSpecialCmd cmd);

    void visitInvokeVirtualCmd(InvokeVirtualCmd cmd);

    void visitLdcCmd(LdcCmd cmd);

    void visitNewObjectCmd(NewObjectCmd cmd);

    void visitNoOperandCmd(NoOperandCmd cmd);

    void visitPutFieldCmd(PutFieldCmd cmd);


}
