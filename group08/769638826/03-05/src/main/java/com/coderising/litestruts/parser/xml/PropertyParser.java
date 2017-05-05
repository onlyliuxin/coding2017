package com.coderising.litestruts.parser.xml;

import java.util.Properties;

/**
 * Created by huitailang on 17/3/5.
 * 属性解析器
 */
public class PropertyParser {
    private PropertyParser() {
        // Prevent Instantiation
    }

    public static String parse(String string, Properties variables) {
        VariableTokenHandler handler = new VariableTokenHandler(variables);
        GenericTokenParser parser = new GenericTokenParser("${", "}", handler);
        return parser.parse(string);
    }

    //就是一个map，用相应的value替换key
    private static class VariableTokenHandler implements TokenHandler {
        private Properties variables;

        public VariableTokenHandler(Properties variables) {
            this.variables = variables;
        }

        @Override
        public String handleToken(String content) {
            if (variables != null && variables.containsKey(content)) {
                return variables.getProperty(content);
            }
            return "${" + content + "}";
        }
    }
}
