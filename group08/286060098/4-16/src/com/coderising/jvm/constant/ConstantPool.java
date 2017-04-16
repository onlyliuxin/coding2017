package com.coderising.jvm.constant;

import com.google.common.collect.Lists;

import java.util.List;

public class ConstantPool {

    private List<ConstantInfo> constantInfos = Lists.newArrayList();

    public ConstantPool() {

    }

    public void addConstantInfo(ConstantInfo info) {

        this.constantInfos.add(info);

    }

    public ConstantInfo getConstantInfo(int index) {
        return this.constantInfos.get(index);
    }

    public String getUTF8String(int index) {
        return ((UTF8Info) this.constantInfos.get(index)).getValue();
    }

    public Object getSize() {
        return this.constantInfos.size() - 1;
    }
}
