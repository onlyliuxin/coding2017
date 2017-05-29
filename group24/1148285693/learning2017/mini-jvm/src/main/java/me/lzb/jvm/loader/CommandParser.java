package me.lzb.jvm.loader;

import me.lzb.common.utils.StringUtils;
import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.cmd.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZB
 */
public class CommandParser {


    private int index;

    private final char[] data;

    public CommandParser(String codes) {
        if (StringUtils.isBlank(codes)) {
            throw new RuntimeException("the orignal code is not correct!");
        }
        codes = codes.toUpperCase();
        data = codes.toCharArray();
    }

    private char[] nextChars(int nextLength) {
        char[] target = new char[nextLength];
        System.arraycopy(data, index, target, 0, nextLength);
        index = index + nextLength;
        return target;
    }

    private int nextCharsToInt(int nextLength) {
        return Integer.valueOf(String.valueOf(nextChars(nextLength)), 16).intValue();
    }

    private String nextCharsToString(int nextLength) {
        return String.valueOf(nextChars(nextLength));
    }


    private String next2CharAsString() {
        return nextCharsToString(2);
    }

    private int next2CharAsInt() {
        return nextCharsToInt(2);
    }

    private boolean hasNext() {
        return index < data.length;
    }


    public ByteCodeCommand[] parse(ClassFile clzFile) {


        List<ByteCodeCommand> cmds = new ArrayList<>();

        //这里用到的指令分为3种，没有操作数的（2个字节），一个操作数（4个字节），两个操作数（6个字节）


        while (hasNext()) {
            String opCode = next2CharAsString();

            if (ByteCodeCommand.new_object.equals(opCode)) {
                NewObjectCmd cmd = new NewObjectCmd(clzFile, opCode);

                cmd.setOprand1(next2CharAsInt());
                cmd.setOprand2(next2CharAsInt());

                cmds.add(cmd);
            } else if (ByteCodeCommand.invokespecial.equals(opCode)) {
                InvokeSpecialCmd cmd = new InvokeSpecialCmd(clzFile, opCode);
                cmd.setOprand1(next2CharAsInt());
                cmd.setOprand2(next2CharAsInt());
                // System.out.println( cmd.toString(clzFile.getConstPool()));
                cmds.add(cmd);
            } else if (ByteCodeCommand.invokevirtual.equals(opCode)) {
                InvokeVirtualCmd cmd = new InvokeVirtualCmd(clzFile, opCode);
                cmd.setOprand1(next2CharAsInt());
                cmd.setOprand2(next2CharAsInt());

                cmds.add(cmd);
            } else if (ByteCodeCommand.getfield.equals(opCode)) {
                GetFieldCmd cmd = new GetFieldCmd(clzFile, opCode);
                cmd.setOprand1(next2CharAsInt());
                cmd.setOprand2(next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.getstatic.equals(opCode)) {
                GetStaticFieldCmd cmd = new GetStaticFieldCmd(clzFile, opCode);
                cmd.setOprand1(next2CharAsInt());
                cmd.setOprand2(next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.putfield.equals(opCode)) {
                PutFieldCmd cmd = new PutFieldCmd(clzFile, opCode);
                cmd.setOprand1(next2CharAsInt());
                cmd.setOprand2(next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.ldc.equals(opCode)) {
                LdcCmd cmd = new LdcCmd(clzFile, opCode);
                cmd.setOperand(next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.bipush.equals(opCode)) {
                BiPushCmd cmd = new BiPushCmd(clzFile, opCode);
                cmd.setOperand(next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.if_icmpge.equals(opCode)
                || ByteCodeCommand.if_icmpgt.equals(opCode)
                || ByteCodeCommand.if_icmple.equals(opCode)
                || ByteCodeCommand.goto_no_condition.equals(opCode)) {
                ComparisonCmd cmd = new ComparisonCmd(clzFile, opCode);
                cmd.setOprand1(next2CharAsInt());
                cmd.setOprand2(next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.iinc.equals(opCode)) {
                IncrementCmd cmd = new IncrementCmd(clzFile, opCode);
                cmd.setOprand1(next2CharAsInt());
                cmd.setOprand2(next2CharAsInt());
                cmds.add(cmd);
            } else if (ByteCodeCommand.dup.equals(opCode)
                || ByteCodeCommand.aload_0.equals(opCode)
                || ByteCodeCommand.aload_1.equals(opCode)
                || ByteCodeCommand.aload_2.equals(opCode)
                || ByteCodeCommand.iload_1.equals(opCode)
                || ByteCodeCommand.iload_2.equals(opCode)
                || ByteCodeCommand.iload_3.equals(opCode)
                || ByteCodeCommand.fload_3.equals(opCode)
                || ByteCodeCommand.iconst_0.equals(opCode)
                || ByteCodeCommand.iconst_1.equals(opCode)
                || ByteCodeCommand.istore_1.equals(opCode)
                || ByteCodeCommand.istore_2.equals(opCode)
                || ByteCodeCommand.voidreturn.equals(opCode)
                || ByteCodeCommand.iadd.equals(opCode)
                || ByteCodeCommand.astore_1.equals(opCode)
                || ByteCodeCommand.ireturn.equals(opCode)) {

                NoOperandCmd cmd = new NoOperandCmd(clzFile, opCode);
                cmds.add(cmd);
            } else {
                throw new RuntimeException("Sorry, the java instruction " + opCode + " has not been implemented");
            }

        }

        calcuateOffset(cmds);

        ByteCodeCommand[] result = new ByteCodeCommand[cmds.size()];
        cmds.toArray(result);
        return result;
    }

    private static void calcuateOffset(List<ByteCodeCommand> cmds) {

        int offset = 0;
        for (ByteCodeCommand cmd : cmds) {
            cmd.setOffset(offset);
            offset += cmd.getLength();
        }

    }

//    private static class CommandIterator {
//        String codes = null;
//        int pos = 0;
//
//        CommandIterator(String codes) {
//            this.codes = codes;
//        }
//
//        public boolean hasNext() {
//            return pos < this.codes.length();
//        }
//
//        public String next2CharAsString() {
//            String result = codes.substring(pos, pos + 2);
//            pos += 2;
//            return result;
//        }
//
//        public int next2CharAsInt() {
//            String s = this.next2CharAsString();
//            return Integer.valueOf(s, 16).intValue();
//        }
//
//    }
}
