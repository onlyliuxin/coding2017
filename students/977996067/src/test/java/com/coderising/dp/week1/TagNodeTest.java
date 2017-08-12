package com.coderising.dp.week1;

import org.junit.Test;

public class TagNodeTest {

    @Test
    public void testBuilder() {

        TagBuilder builder = new TagBuilder("order");


        String xml = builder.addChild("line-items")
                .addChild("line-item").setAttribute("pid", "p3333").setAttribute("qty", "3")
                .addSibling("line-item").setAttribute("pid", "p9876").setAttribute("qty", "10")
                .toXML();

        System.out.println(xml);
    }
}