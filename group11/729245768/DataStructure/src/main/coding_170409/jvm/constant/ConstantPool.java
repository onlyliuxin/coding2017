package main.coding_170409.jvm.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 2017/4/21.
 */
public class ConstantPool {
    private List<ConstantInfo> constantInfos = new ArrayList<>();

    public ConstantPool(){}

    public void addConstantInfo(ConstantInfo constantInfo){
        this.constantInfos.add(constantInfo);
    }
    public ConstantInfo getConstantInfo(int index){
        return  this.constantInfos.get(index);
    }
    public String getUTF8String(int index){
        return ((UTF8Info)this.constantInfos.get(index)).getValue();
    }
    public Object getSizes(){
        return this.constantInfos.size()-1;
    }
}
