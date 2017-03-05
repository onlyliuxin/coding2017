package com.coderising.litestruts.parser;

import org.junit.Test;

/**
 * Created by luoziyihao on 3/5/17.
 */
public class StructsParserTest {
    @Test
    public void parser() throws Exception {
        new DefaultStrutsParser().parser("struts.xml");
    }

}