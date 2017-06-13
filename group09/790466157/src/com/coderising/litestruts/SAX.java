package com.coderising.litestruts;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
public class SAX extends DefaultHandler {
  //文档开始事件处理方法
  public void startDocument() {
  	System.out.println("文档开始 ");
  }
  //文档结束事件处理方法    
  public void endDocument()  {
  	System.out.println("文档结束");
  }
  //元素开始事件处理方法  
  public void startElement(String uri, String localName, String qname, Attributes attr)
  {    System.out.println("元素开始: 本地名: " + localName + " 限定名: " + qname + "  命名空间URI: "+uri);
    int attrCount = attr.getLength();
    if(attrCount>0) {
      System.out.println("属性:"); 
      for(int i = 0 ; i<attrCount ; i++) {
        System.out.println("  属性名 : " + attr.getQName(i)); 
        System.out.println("  属性类型 : " + attr.getType(i)); 
        System.out.println("  属性值: " + attr.getValue(i));       }    }     }
//元素结束事件处理方法  
  public void endElement(String uri, String localName, String qname) {
    System.out.println("元素结束: 本地名: " + localName + " 限定名: "  + qname + "  命名空间URI: "+uri);  }
   //文本事件处理方法  
  public void characters(char[] ch, int start, int length) {
    System.out.println("文本字符: " + new String(ch, start, length));      }
  //空白字符事件处理方法  
  public void ignorableWhitespace(char[] ch, int start, int length) {    System.out.println("忽略的空白: " + length + " Characters.");       }  }
