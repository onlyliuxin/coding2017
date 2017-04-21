package jvm.print;

import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.Constant;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConstantPoolPrinter {
    ConstantPool pool;

    ConstantPoolPrinter(ConstantPool pool) {
        this.pool = pool;
    }

    public void print() {
        System.out.println("Constant Pool:");
        final List<Map<Integer, String>> maps = new LinkedList<>();
        pool.forEach(c -> maps.add(c.printableMap()));
        maps.remove(0);

        int[] maxLens = getMaxValueLengths(maps);

        String format = "%" + ((maps.size() + "").length() + 3)
                + "s = %" + ((maxLens[0] + 8) > 0 ? "-" + (maxLens[0] + 8) : "")
                + "s%" + ((maxLens[1] + 8) > 0 ? "-" + (maxLens[1] + 8) : "")
                + "s%s\n";

        for (int i = 0; i < maps.size(); ++i) {
            Map<Integer, String> m = maps.get(i);
            String type = m.get(Constant.PRINT_TYPE);
            String param = m.get(Constant.PRINT_PARAM);
            String comment = m.get(Constant.PRINT_COMMENT);
            System.out.printf(format, "#" + (i + 1),
                    type == null ? "" : type,
                    param == null ? "" : param,
                    comment == null ? "" : comment);
        }
    }

    private int[] getMaxValueLengths(List<Map<Integer, String>> maps) {
        int maxLen[] = new int[2];
        for (Map<Integer, String> m : maps) {
            String type = m.get(Constant.PRINT_TYPE);
            if (type != null && type.length() > maxLen[0]) {
                maxLen[0] = type.length();
            }
            if (type == null || !"utf8".equals(type.toLowerCase())) {
                String param = m.get(Constant.PRINT_PARAM);
                if (param != null && param.length() > maxLen[1]) {
                    maxLen[1] = param.length();
                }
            }
        }
        return maxLen;
    }
}
