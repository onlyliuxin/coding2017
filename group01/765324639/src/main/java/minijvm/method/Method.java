package minijvm.method;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import minijvm.attr.CodeAttr;
import minijvm.clz.ClassFile;
import minijvm.cmd.ByteCodeCommand;
import minijvm.constant.ConstantPool;
import minijvm.constant.UTF8Info;
import minijvm.loader.ByteCodeIterator;

public class Method {
    
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    
    private CodeAttr codeAttr;
    
    private ClassFile clzFile;
    
    
    public ClassFile getClzFile() {
        return clzFile;
    }

    public int getNameIndex() {
        return nameIndex;
    }
    public int getDescriptorIndex() {
        return descriptorIndex;
    }
    
    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr code) {
        this.codeAttr = code;
    }
    
    

    public Method(ClassFile clzFile,int accessFlag, int nameIndex, int descriptorIndex) {
        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    
    
    
    
    @Override
    public String toString() {
        
        ConstantPool pool = this.clzFile.getConstantPool();
        StringBuilder buffer = new StringBuilder();
        
        String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
        
        String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
        
        buffer.append(name).append(":").append(desc).append("\n");
        
        buffer.append(this.codeAttr.toString(pool));
        
        return buffer.toString();
    }
    
    public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
        return null;
        
    }

    public ByteCodeCommand[] getCmds() {        
        return this.getCodeAttr().getCmds();
    }
    
    private String getParamAndReturnType() {
        UTF8Info nameAndTypeInfo = (UTF8Info)this.getClzFile().getConstantPool().getConstantInfo(this.getDescriptorIndex());
        return nameAndTypeInfo.getValue();
    }

    public List<String> getParameterList() {
        String paramAndType = getParamAndReturnType();
        
        // 获取参数和返回类型中的参数部分
        int first = paramAndType.indexOf("(");
        int last = paramAndType.lastIndexOf(")");
        String param = paramAndType.substring(first + 1, last);
        
        List<String> paramList = new ArrayList<>();
        if (StringUtils.isEmpty(param)) {
            return paramList;
        }
        
        while (!"".equals(param)) {
            int pos = 0;
            // 对象类型（以'L'开头，以';'结尾）
            if (param.charAt(pos) == 'L') {
                int end = param.indexOf(";");
                if (end == -1) {
                    throw new RuntimeException("对象类型没有以;结尾");
                }
                paramList.add(param.substring(pos + 1, end));
                pos = end + 1;
            } else if (param.charAt(pos) == 'I') { // int类型
                paramList.add("I");
                pos++;
            } else if (param.charAt(pos) == 'F') { // float类型
                paramList.add("F");
                pos++;
            } else {
                throw new RuntimeException("这个参数类型还没有实现" + param.charAt(pos));
            }
            param = param.substring(pos);
        }
        return paramList;
    }
}
