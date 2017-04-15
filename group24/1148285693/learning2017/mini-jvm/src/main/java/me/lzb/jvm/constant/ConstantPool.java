package me.lzb.jvm.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZB on 2017/4/14.
 */
public class ConstantPool {

    private List<ConstantInfo> constantInfoList = new ArrayList<>();



    public void addConstantInfo(ConstantInfo constantInfo){
        constantInfoList.add(constantInfo);
    }


    public int getSize(){
        return constantInfoList.size() > 1 ? constantInfoList.size() - 1 : 0;
    }

    public ConstantInfo getConstantInfo(int index){
        return constantInfoList.get(index);
    }

    public String getUTF8String(int index){
        return ((UTF8Info)this.constantInfoList.get(index)).getValue();
    }

}
