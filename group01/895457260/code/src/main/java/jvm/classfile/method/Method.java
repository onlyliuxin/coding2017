package jvm.classfile.method;

import jvm.classfile.AccessFlag;
import jvm.classfile.ClassFile;
import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.attribute.item.impl.CodeAttr;
import jvm.classfile.attribute.parser.AttributeParser;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.UTF8Info;
import jvm.command.CommandParser;
import jvm.command.item.ByteCodeCommand;
import jvm.util.ByteCodeIterator;
import jvm.util.TypeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Method {
    private AccessFlag accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private ConstantPool constantPool;
    private List<AttributeInfo> attributes = new ArrayList<>();
    private ByteCodeCommand[] commands;

    public Method(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool constantPool) {
        this.accessFlag = new AccessFlag(accessFlag);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.constantPool = constantPool;
    }

    public static Method parse(ByteCodeIterator iterator, ClassFile classFile) {
        int access = iterator.nextU2ToInt();
        int name = iterator.nextU2ToInt();
        int descriptor = iterator.nextU2ToInt();
        int attrCount = iterator.nextU2ToInt();
        Method result = new Method(access, name, descriptor, classFile.getConstantPool());
        for (int i = 0; i < attrCount; ++i) {
            result.attributes.add(AttributeParser.parse(iterator, classFile.getConstantPool()));
        }
        CodeAttr codeAttr = (CodeAttr) result.attributes.stream()
                .filter(a -> a instanceof CodeAttr).findFirst().orElse(null);
        if (codeAttr != null) {
            result.commands = CommandParser.parse(classFile, codeAttr.getCode());
        }
        return result;
    }

    public String[] getParamTypes() {
        String str = getParamAndReturnType();
        str = str.substring(str.indexOf('(') + 1, str.lastIndexOf(')'));
        String[] split = Arrays.stream(str.split("[L;]"))
                .filter(s -> !"".equals(s))
                .toArray(String[]::new);

        List<String> result = new ArrayList<>();
        Arrays.stream(split).forEach(s ->
                result.add(s.length() == 1 ?
                        TypeUtils.parse(s) : s));

        return result.toArray(new String[result.size()]);
    }

    public int getParamCount() {
        return getParamTypes().length;
    }

    public AccessFlag getAccessFlag() {
        return accessFlag;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public ByteCodeCommand[] getCommands() {
        return commands;
    }

    public String getName() {
        return ((UTF8Info) getConstantPool().getConstantInfo(getNameIndex())).getValue();
    }

    public String getParamAndReturnType() {
        return ((UTF8Info) getConstantPool().getConstantInfo(getDescriptorIndex())).getValue();
    }

    @Override
    public String toString() {
        return getName() + ':' + getParamAndReturnType();
    }
}
