package main.coding_170430.jvm.cmd;

import main.coding_170430.jvm.clz.ClassFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterchen on 2017/4/26.
 */
public class CommandParser {

    public static ByteCodeCommand[] parse(ClassFile clzFile, String codes) {
        if (codes == null || codes.length() == 0 || (codes.length() % 2) != 0) {
            throw new RuntimeException("the original code is not correct");
        }

        codes = codes.toUpperCase();

        CommandIterator commandIterator = new CommandIterator(codes);
        List<ByteCodeCommand> byteCodeCommands = new ArrayList<>();
        while (commandIterator.hasNext()) {
            String opCode = commandIterator.next2CharAsString();
            switch (opCode) {
                case ByteCodeCommand.new_object:
                    NewObjectCmd newObjectCmd = new NewObjectCmd(clzFile, opCode);
                    newObjectCmd.setOprand1(commandIterator.nextCharAsInt());
                    newObjectCmd.setOprand2(commandIterator.nextCharAsInt());
                    byteCodeCommands.add(newObjectCmd);
                    break;
                case ByteCodeCommand.invokespecial:
                    InvokeSpecialCmd invokeSpecialCmd = new InvokeSpecialCmd(clzFile, opCode);
                    invokeSpecialCmd.setOprand1(commandIterator.nextCharAsInt());
                    invokeSpecialCmd.setOprand2(commandIterator.nextCharAsInt());
                    byteCodeCommands.add(invokeSpecialCmd);
                    break;
                case ByteCodeCommand.invokevirtual:
                    InvokeVirtualCmd invokeVirtualCmd = new InvokeVirtualCmd(clzFile, opCode);
                    invokeVirtualCmd.setOprand1(commandIterator.nextCharAsInt());
                    invokeVirtualCmd.setOprand2(commandIterator.nextCharAsInt());
                    byteCodeCommands.add(invokeVirtualCmd);
                    break;
                case ByteCodeCommand.getfield:
                    GetFieldCmd getFieldCmd = new GetFieldCmd(clzFile, opCode);
                    getFieldCmd.setOprand1(commandIterator.nextCharAsInt());
                    getFieldCmd.setOprand2(commandIterator.nextCharAsInt());
                    byteCodeCommands.add(getFieldCmd);
                    break;
                case ByteCodeCommand.getstatic:
                    GetStaticFieldCmd getStaticFieldCmd = new GetStaticFieldCmd(clzFile, opCode);
                    getStaticFieldCmd.setOprand1(commandIterator.nextCharAsInt());
                    getStaticFieldCmd.setOprand2(commandIterator.nextCharAsInt());
                    byteCodeCommands.add(getStaticFieldCmd);
                    break;
                case ByteCodeCommand.putfield:
                    PutFieldCmd putFieldCmd = new PutFieldCmd(clzFile, opCode);
                    putFieldCmd.setOprand1(commandIterator.nextCharAsInt());
                    putFieldCmd.setOprand2(commandIterator.nextCharAsInt());
                    byteCodeCommands.add(putFieldCmd);
                    break;
                case ByteCodeCommand.ldc:
                    LdcCmd ldcCmd = new LdcCmd(clzFile, opCode);
                    ldcCmd.setOperand(commandIterator.nextCharAsInt());
                    byteCodeCommands.add(ldcCmd);
                    break;
                case ByteCodeCommand.bipush:
                    BiPushCmd biPushCmd = new BiPushCmd(clzFile, opCode);
                    biPushCmd.setOperand(commandIterator.nextCharAsInt());
                    byteCodeCommands.add(biPushCmd);
                    break;
                case ByteCodeCommand.dup:
                case ByteCodeCommand.aload_0:
                case ByteCodeCommand.aload_1:
                case ByteCodeCommand.aload_2:
                case ByteCodeCommand.iload_1:
                case ByteCodeCommand.iload_2:
                case ByteCodeCommand.iload_3:
                case ByteCodeCommand.fload_3:
                case ByteCodeCommand.voidreturn:
                case ByteCodeCommand.astore_1:
                    NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, opCode);
                    byteCodeCommands.add(noOperandCmd);
                    break;
                default:
                    throw new RuntimeException("Sorry,the instruction " + opCode + " " + "has not implemented");

            }
        }
        calcuateOffset(byteCodeCommands);
        ByteCodeCommand[] result = new ByteCodeCommand[byteCodeCommands.size()];
        byteCodeCommands.toArray(result);
        return result;
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

        CommandIterator(String code) {
            this.codes = codes;
        }

        public boolean hasNext() {
            return pos < this.codes.length();
        }

        public String next2CharAsString() {
            String result = codes.substring(pos, pos + 2);
            pos += 2;
            return result;
        }

        public int nextCharAsInt() {
            String s = this.next2CharAsString();
            return Integer.valueOf(s, 16).intValue();
        }
    }
}
