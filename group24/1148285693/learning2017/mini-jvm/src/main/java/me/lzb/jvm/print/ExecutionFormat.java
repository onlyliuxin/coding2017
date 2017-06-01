package me.lzb.jvm.print;

import me.lzb.common.utils.StringUtils;
import me.lzb.jvm.cmd.*;
import me.lzb.jvm.constant.*;

/**
 * @author LZB
 */
public class ExecutionFormat implements ExecutionVisitor {

    private static final int one = 19;

    private static final int two = 19;

    private static ExecutionFormat format = null;

    public static ExecutionFormat getInstance() {
        if (format == null) {
            format = new ExecutionFormat();
        }
        return format;
    }

    private ExecutionFormat() {

    }


    @Override
    public void visitBiPushCmd(BiPushCmd cmd) {
        System.out.println(getOffset(cmd.getOffset()) + ":" + cmd.getOpCode() + " " + StringUtils.appendSpace(one, cmd.getReadableCodeText()) + cmd.getOperand());
    }

    @Override
    public void visitGetFieldCmd(GetFieldCmd cmd) {
        exFile(cmd);
    }

    @Override
    public void visitGetStaticFieldCmd(GetStaticFieldCmd cmd) {
        exFile(cmd);
    }

    @Override
    public void visitInvokeSpecialCmd(InvokeSpecialCmd cmd) {
        exMethod(cmd);
    }

    @Override
    public void visitInvokeVirtualCmd(InvokeVirtualCmd cmd) {
        exMethod(cmd);
    }

    @Override
    public void visitLdcCmd(LdcCmd cmd) {
        ConstantInfo info = cmd.getConstantInfo(cmd.getOperand());

        String value = "TBD";
        if (info instanceof StringInfo) {
            StringInfo strInfo = (StringInfo) info;
            value = strInfo.toString();
        }

        System.out.println(getOffset(cmd.getOffset()) + ":" + cmd.getOpCode() + " " + StringUtils.appendSpace(one, cmd.getReadableCodeText()) + "#" + StringUtils.appendSpace(two, cmd.getOperand() + "") + "// String  " + value);
    }

    @Override
    public void visitNewObjectCmd(NewObjectCmd cmd) {
        int index = cmd.getIndex();
        String codeTxt = cmd.getReadableCodeText();
        ClassInfo info = (ClassInfo) cmd.getConstantInfo(index);
        System.out.println(getOffset(cmd.getOffset()) + ":" + cmd.getOpCode() + " " + StringUtils.appendSpace(one, codeTxt) + "#" + StringUtils.appendSpace(two, index + "") + "// class   " + info.getClassName());
    }

    @Override
    public void visitNoOperandCmd(NoOperandCmd cmd) {
        System.out.println(getOffset(cmd.getOffset()) + ":" + cmd.getOpCode() + " " + cmd.getReadableCodeText());
    }

    @Override
    public void visitPutFieldCmd(PutFieldCmd cmd) {
        exFile(cmd);
    }


    @Override
    public void visitComparisonCmd(ComparisonCmd cmd) {
        //TODO 执行输出格式
        String codeTxt = cmd.getReadableCodeText();
        System.out.println(getOffset(cmd.getOffset()) + ":" + cmd.getOpCode() + " " + StringUtils.appendSpace(one, codeTxt) + cmd.getGoOffset());
    }

    @Override
    public void visitIncrementCmd(IncrementCmd cmd) {
        //TODO 执行输出格式
        String codeTxt = cmd.getReadableCodeText();
        System.out.println(getOffset(cmd.getOffset()) + ":" + cmd.getOpCode() + " " + StringUtils.appendSpace(one, codeTxt));
    }


    private void exFile(TwoOperandCmd cmd) {
        int index = cmd.getIndex();
        String codeTxt = cmd.getReadableCodeText();
        FieldRefInfo info = (FieldRefInfo) cmd.getConstantInfo(index);
        System.out.println(getOffset(cmd.getOffset()) + ":" + cmd.getOpCode() + " " + StringUtils.appendSpace(one, codeTxt) + "#" + StringUtils.appendSpace(two, index + "") + "// Field  " + info.toString());
    }

    private void exMethod(TwoOperandCmd cmd) {
        int index = cmd.getIndex();
        String codeTxt = cmd.getReadableCodeText();
        MethodRefInfo info = (MethodRefInfo) cmd.getConstantInfo(index);
        System.out.println(getOffset(cmd.getOffset()) + ":" + cmd.getOpCode() + " " + StringUtils.appendSpace(one, codeTxt) + "#" + StringUtils.appendSpace(two, index + "") + "// Method  " + info.toString());
    }


    private String getOffset(int offset) {
        String s = String.valueOf(offset);
        if (s.length() == 1) {
            s = " " + s;
        }
        return s;


    }
}
