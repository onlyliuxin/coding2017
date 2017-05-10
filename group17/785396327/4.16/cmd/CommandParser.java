package cmd;

import clz.ClassFile;
import constant.ClassInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william on 2017/4/17.
 */
public class CommandParser {
    public static final String aconst_null = "01";
    public static final String new_object = "BB";
    public static final String lstore = "37";
    public static final String invokespecial = "B7";
    public static final String invokevirtual = "B6";
    public static final String getfield = "B4";
    public static final String putfield = "B5";
    public static final String getstatic = "B2";
    public static final String ldc = "12";
    public static final String dup = "59";
    public static final String bipush = "10";
    public static final String aload_0 = "2A";
    public static final String aload_1 = "2B";
    public static final String aload_2 = "2C";
    public static final String iload = "15";
    public static final String iload_1 = "1B";
    public static final String iload_2 = "1C";
    public static final String iload_3 = "1D";
    public static final String fload_3 = "25";

    public static final String voidreturn = "B1";
    public static final String ireturn = "AC";
    public static final String freturn = "AE";

    public static final String astore_1 = "4C";
    public static final String if_icmp_ge = "A2";
    public static final String if_icmple = "A4";
    public static final String goto_no_condition = "A7";
    public static final String iconst_0 = "03";
    public static final String iconst_1 = "04";
    public static final String istore_1 = "3C";
    public static final String istore_2 = "3D";
    public static final String iadd = "60";
    public static final String iinc = "84";

    public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
        CommandIterator commandIterator = new CommandIterator(codes);
        List<ByteCodeCommand> commandList = new ArrayList<ByteCodeCommand>();
        while (commandIterator.hasNext()) {
            String command = commandIterator.next2CharAsString().toUpperCase();
            if (command.equals(aconst_null)) {
                NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
                noOperandCmd.setOffset(noOperandCmd.getLength());
                commandList.add(noOperandCmd);
            } else if (command.equals(new_object)) {
                NewObjectCmd newObjectCmd = new NewObjectCmd(clzFile, command);
                newObjectCmd.setOprand1(commandIterator.next2CharAsInt());
                newObjectCmd.setOprand2(commandIterator.next2CharAsInt());
                newObjectCmd.setOffset(newObjectCmd.getLength());
                commandList.add(newObjectCmd);
            } else if (command.equals(lstore)) {

            } else if (command.equals(invokespecial)) {
                InvokeSpecialCmd invokeSpecialCmd = new InvokeSpecialCmd(clzFile, command);
                invokeSpecialCmd.setOprand1(commandIterator.next2CharAsInt());
                invokeSpecialCmd.setOprand2(commandIterator.next2CharAsInt());
                invokeSpecialCmd.setOffset(invokeSpecialCmd.getLength());
                commandList.add(invokeSpecialCmd);
            } else if (command.equals(invokevirtual)) {
                InvokeVirtualCmd invokeVirtualCmd = new InvokeVirtualCmd(clzFile, command);
                invokeVirtualCmd.setOprand1(commandIterator.next2CharAsInt());
                invokeVirtualCmd.setOprand2(commandIterator.next2CharAsInt());
                invokeVirtualCmd.setOffset(invokeVirtualCmd.getLength());
                commandList.add(invokeVirtualCmd);
            } else if (command.equals(getfield)) {
                GetFieldCmd getFieldCmd = new GetFieldCmd(clzFile, command);
                getFieldCmd.setOprand1(commandIterator.next2CharAsInt());
                getFieldCmd.setOprand2(commandIterator.next2CharAsInt());
                getFieldCmd.setOffset(getFieldCmd.getLength());
                commandList.add(getFieldCmd);
            } else if (command.equals(putfield)) {
                PutFieldCmd putFieldCmd = new PutFieldCmd(clzFile, command);
                putFieldCmd.setOprand1(commandIterator.next2CharAsInt());
                putFieldCmd.setOprand2(commandIterator.next2CharAsInt());
                putFieldCmd.setOffset(putFieldCmd.getLength());
                commandList.add(putFieldCmd);
            } else if (command.equals(getstatic)) {
                GetStaticFieldCmd getStaticFieldCmd = new GetStaticFieldCmd(clzFile, command);
                getStaticFieldCmd.setOprand1(commandIterator.next2CharAsInt());
                getStaticFieldCmd.setOprand2(commandIterator.next2CharAsInt());
                getStaticFieldCmd.setOffset(getStaticFieldCmd.getLength());
                commandList.add(getStaticFieldCmd);
            } else if (command.equals(ldc)) {
                LdcCmd ldcCmd = new LdcCmd(clzFile, command);
                ldcCmd.setOperand(commandIterator.next2CharAsInt());
                ldcCmd.setOffset(ldcCmd.getLength());
                commandList.add(ldcCmd);
            } else if (command.equals(dup)) {
                NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
                noOperandCmd.setOffset(noOperandCmd.getLength());
                commandList.add(noOperandCmd);
            } else if (command.equals(bipush)) {
                BiPushCmd biPushCmd = new BiPushCmd(clzFile, command);
                biPushCmd.setOperand(commandIterator.next2CharAsInt());
                biPushCmd.setOffset(biPushCmd.getLength());
                commandList.add(biPushCmd);
            } else if (command.equals(aload_0)) {
                NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
                noOperandCmd.setOffset(noOperandCmd.getLength());
                commandList.add(noOperandCmd);
            } else if (command.equals(aload_1)) {
                NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
                noOperandCmd.setOffset(noOperandCmd.getLength());
                commandList.add(noOperandCmd);
            } else if (command.equals(aload_2)) {
                NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
                noOperandCmd.setOffset(noOperandCmd.getLength());
                commandList.add(noOperandCmd);
            } else if (command.equals(iload)) {

            } else if (command.equals(iload_2)) {
                NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
                noOperandCmd.setOffset(noOperandCmd.getLength());
                commandList.add(noOperandCmd);
            } else if (command.equals(iload_1)) {
                NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
                noOperandCmd.setOffset(noOperandCmd.getLength());
                commandList.add(noOperandCmd);
            } else if (command.equals(iload_3)) {
                NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
                noOperandCmd.setOffset(noOperandCmd.getLength());
                commandList.add(noOperandCmd);
            } else if (command.equals(fload_3)) {

            } else if (command.equals(voidreturn)) {
                NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
                noOperandCmd.setOffset(noOperandCmd.getLength());
                commandList.add(noOperandCmd);
            } else if (command.equals(ireturn)) {

            } else if (command.equals(freturn)) {

            } else if (command.equals(astore_1)) {
                NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, command);
                noOperandCmd.setOffset(noOperandCmd.getLength());
                commandList.add(noOperandCmd);
            } else if (command.equals(if_icmp_ge)) {

            } else if (command.equals(if_icmple)) {

            } else if (command.equals(goto_no_condition)) {

            } else if (command.equals(iconst_0)) {

            } else if (command.equals(iconst_1)) {

            } else if (command.equals(istore_1)) {

            } else if (command.equals(istore_2)) {

            } else if (command.equals(iadd)) {

            } else if (command.equals(iinc)) {

            } else {
                throw new RuntimeException("wrong command : " + command);
            }
        }
        calcuateOffset(commandList);
        return commandList.toArray(new ByteCodeCommand[commandList.size()]);
    }

    private static void calcuateOffset(List<ByteCodeCommand> cmds) {

        int offset = 0;
        for (ByteCodeCommand cmd : cmds) {
            cmd.setOffset(offset);
            offset += cmd.getLength();
        }

    }

    private static class CommandIterator {
        String codes = null;
        int pos = 0;

        CommandIterator(String codes) {
            this.codes = codes;
        }

        public boolean hasNext() {
            return pos < this.codes.length();
        }

        /**
         * 从字符串中截取前两个字符
         *
         * @return
         */
        public String next2CharAsString() {
            String result = codes.substring(pos, pos + 2);
            pos += 2;
            return result;
        }

        public int next2CharAsInt() {
            String s = this.next2CharAsString();
            return Integer.valueOf(s, 16).intValue();
        }

    }
}
