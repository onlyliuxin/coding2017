package com.github.wdn.coding2017.jvm.cmd;

import com.github.wdn.coding2017.jvm.clz.ClassFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public class CommandParser {
    public static final String nop = "00";
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
        List<ByteCodeCommand> cmdList = new ArrayList<>();
        CommandIterator commandIterator = new CommandIterator(codes);
        while (commandIterator.hasNext()) {
            String cmdCode = commandIterator.read2ToString();
            cmdCode = cmdCode.toUpperCase();
            if (cmdCode.equals(new_object)) {
                NewObjectCmd cmd = new NewObjectCmd(clzFile, cmdCode);
                cmd.setOprand1(commandIterator.read2ToInt());
                cmd.setOprand2(commandIterator.read2ToInt());
                cmdList.add(cmd);
            }else if (cmdCode.equals(invokespecial)) {
                InvokeSpecialCmd cmd = new InvokeSpecialCmd(clzFile,cmdCode);
                cmd.setOprand1(commandIterator.read2ToInt());
                cmd.setOprand2(commandIterator.read2ToInt());
                cmdList.add(cmd);
            } else if (getstatic.equals(cmdCode)) {
                GetStaticFieldCmd cmd = new GetStaticFieldCmd(clzFile, cmdCode);
                cmd.setOprand1(commandIterator.read2ToInt());
                cmd.setOprand2(commandIterator.read2ToInt());
                cmdList.add(cmd);
            } else if (putfield.equals(cmdCode)) {
                PutFieldCmd cmd = new PutFieldCmd(clzFile, cmdCode);
                cmd.setOprand1(commandIterator.read2ToInt());
                cmd.setOprand2(commandIterator.read2ToInt());
                cmdList.add(cmd);
            } else if (ldc.equals(cmdCode)) {
                LdcCmd cmd = new LdcCmd(clzFile, cmdCode);
                cmd.setOperand(commandIterator.read2ToInt());
                cmdList.add(cmd);
            } else if (bipush.equals(cmdCode)) {
                BiPushCmd cmd = new BiPushCmd(clzFile, cmdCode);
                cmd.setOperand(commandIterator.read2ToInt());
                cmdList.add(cmd);
            } else if (invokevirtual.equals(cmdCode)) {
                InvokeVirtualCmd cmd = new InvokeVirtualCmd(clzFile, cmdCode);
                cmd.setOprand1(commandIterator.read2ToInt());
                cmd.setOprand2(commandIterator.read2ToInt());
                cmdList.add(cmd);
            }else if (dup.equals(cmdCode) || aload_0.equals(cmdCode) || aload_1.equals(cmdCode) || aload_2.equals(cmdCode)
                    || iload_1.equals(cmdCode) || iload_2.equals(cmdCode) || iload_3.equals(cmdCode)
                    || fload_3.equals(cmdCode) || voidreturn.equals(cmdCode) || astore_1.equals(cmdCode)) {

                NoOperandCmd cmd = new NoOperandCmd(clzFile, cmdCode);
                cmdList.add(cmd);
            }else{
                throw new RuntimeException("未知的指令类型:"+cmdCode);
            }
        }
        calcuateOffset(cmdList);
        ByteCodeCommand[] result = new ByteCodeCommand[cmdList.size()];
        cmdList.toArray(result);
        return result;
    }
    private static void calcuateOffset(List<ByteCodeCommand> cmds) {
        int offset = 0;
        for (ByteCodeCommand cmd : cmds) {
            cmd.setOffset(offset);
            offset += cmd.getLength();
        }
    }
    private static class CommandIterator{
        private String codes;
        int offset = 0;
        public CommandIterator(String codes){
            this.codes = codes;
        }
        public boolean hasNext(){
            return offset < codes.length();
        }
        public int read2ToInt(){
            String s = this.read2ToString();
            return Integer.valueOf(s, 16).intValue();
        }
        public String read2ToString(){
            String result = codes.substring(offset,offset+2);
            offset+=2;
            return result;
        }
    }

}
