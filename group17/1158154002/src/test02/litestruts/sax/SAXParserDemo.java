package test02.litestruts.sax;

import java.util.HashMap;

import test02.litestruts.Action;

public class SAXParserDemo{
    
    public static Action run( ){
        //创建实例
        SAXParser parser = SAXParser.getInstance();
        //为解析器设置好各个事件的回调函数
        parser.setHandler(new SAXParserHandler() {
            //创建好自定义变量，用以记录XML文档中需要的数据
            String resultName = "";
            String className = "";
            HashMap<String, String> resultJspMap = new HashMap<>();

            boolean foundClass = false;

            //当解析开始时调用
            @Override
            public void startDocument() {
                System.out.println("Start parsing");
            }

            //当完成一个XML开始标签（例如<XXX>）的解析时调用
            @Override
            public void startElement(String tagName, HashMap<String, String> attributes) {
                if(tagName.equals("action")){
                    if(attributes.get("name").equals("login")){
                        className = attributes.get("class");
                        foundClass = true;
                    }else{
                        foundClass = false;
                    }
                }else if(tagName.equals("result") && foundClass){
                    resultName = attributes.get("name");
                }
            }

           //当完成一个XML结束标签（例如</XXX>）的解析时调用
            @Override
            public void endElement(String tagName) {

            }

           //当一段XML标签之间的内容被解析完成时调用
            @Override
            public void innerText(String innerText) {
                if(foundClass){
                    String jsp = innerText;
                    resultJspMap.put(resultName,jsp);
                }
            }

            @Override
            //当解析器读到XML文档结尾时调用
            public Action endDocument() {
            	
                System.out.println(className);
                System.out.println(resultJspMap);
                System.out.println("End parsing");
                
                Action action=new Action();
                action.setClassName(className);
                action.setResultJspMap(resultJspMap);
                return action;
            }
        });

    //调用此方式，开始解析    
        return parser.parse("src/test02/litestruts/struts.xml");
    }
    
}