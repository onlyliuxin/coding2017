package me.lzb.jvm.method;

import me.lzb.common.utils.StringUtils;
import me.lzb.jvm.attr.CodeAttr;
import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.cmd.ByteCodeCommand;
import me.lzb.jvm.constant.UTF8Info;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZB
 */
public class Method {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private CodeAttr codeAttr;

    private ClassFile clzFile;


    public Method(ClassFile clzFile, int accessFlag, int nameIndex, int descriptorIndex) {
        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }


    public int getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr codeAttr) {
        this.codeAttr = codeAttr;
    }

    public ClassFile getClzFile() {
        return clzFile;
    }

    public void setClzFile(ClassFile clzFile) {
        this.clzFile = clzFile;
    }

    public ByteCodeCommand[] getCmds() {
        return this.getCodeAttr().getCmds();
    }


    private String getParamAndReturnType() {
        UTF8Info paramAndReturnTypeInfo = (UTF8Info) getClzFile().getConstantPool().getConstantInfo(getDescriptorIndex());
        return paramAndReturnTypeInfo.getValue();
    }

    public List<String> getParameterList() {

        //(Ljava/lang/String;I)V
        String paramAndType = getParamAndReturnType();

        int first = paramAndType.indexOf("(");
        int last = paramAndType.lastIndexOf(")");

        // Ljava/lang/String;I
        String param = paramAndType.substring(first + 1, last);

        List<String> paramList = new ArrayList<>();

        if (StringUtils.isBlank(param)) {
            return paramList;
        }

        while (StringUtils.isNotBlank(param)) {

            int pos = 0;
            // 这是一个对象类型
            if (param.charAt(pos) == 'L') {

                int end = param.indexOf(";");

                if (end == -1) {
                    throw new RuntimeException("对象类型参数结尾没有\";\"");
                }
                paramList.add(param.substring(pos + 1, end));

                pos = end + 1;

            } else if (param.charAt(pos) == 'I') {
                // int
                paramList.add("I");
                pos++;

            } else if (param.charAt(pos) == 'F') {
                // float
                paramList.add("F");
                pos++;

            } else {
                throw new RuntimeException("the param has unsupported type:" + param);
            }

            param = param.substring(pos);

        }
        return paramList;

    }
}
