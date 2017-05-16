package me.lzb.jvm.print;

import me.lzb.jvm.cmd.*;

/**
 * @author LZB
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

    void visitComparisonCmd(ComparisonCmd cmd);

    void visitIncrementCmd(IncrementCmd cmd);
}
