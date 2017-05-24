package com.github.miniyk2012.coding2017.coderising.jvm.print;

/**
 * Created by thomas_young on 6/5/2017.
 */
public interface Visitable {
    public void accept(ConstantPoolPrinter.Visitor visitor);
}
