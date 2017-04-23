package com.coding.week6.exprNew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/22 0022.
 */
public enum Operator {
    ADD("+", 1) {
        public float apply(float x, float y){
            return x + y;
        }
    },

    SUB("-", 1) {
        @Override
        public float apply(float x, float y) {
            return x - y;
        }
    },

    MULT("*", 2) {
        @Override
        public float apply(float x, float y) {
            return x * y;
        }
    },

    DIVI("/", 2) {
        @Override
        public float apply(float x, float y) {
            return x / y;
        }
    };
    private String symbol;
    private int    priority;

    Operator(String symbol, int priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public boolean hasHigherPriority(Operator o) {
        return this.priority > o.priority;
    }

    public String symbol() {
        return symbol;
    }

    public static List<String> symbols() {
        List<String> symbos = new ArrayList<>();
        for (Operator o : Operator.values()) {
            symbos.add(o.symbol);
        }
        return symbos;
    }

    public abstract float apply(float x, float y);

    private static final Map<String, Operator> map = new HashMap<String, Operator>();

    static {
        for (Operator o : Operator.values()) {
            map.put(o.symbol, o);
        }
    }

    public static Map<String, Operator> getOperatorMap() {
        return map;
    }
}
