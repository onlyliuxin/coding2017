package com.coderising.litestruts;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
public class SAX extends DefaultHandler {
  //�ĵ���ʼ�¼�������
  public void startDocument() {
  	System.out.println("�ĵ���ʼ ");
  }
  //�ĵ������¼�������    
  public void endDocument()  {
  	System.out.println("�ĵ�����");
  }
  //Ԫ�ؿ�ʼ�¼�������  
  public void startElement(String uri, String localName, String qname, Attributes attr)
  {    System.out.println("Ԫ�ؿ�ʼ: ������: " + localName + " �޶���: " + qname + "  �����ռ�URI: "+uri);
    int attrCount = attr.getLength();
    if(attrCount>0) {
      System.out.println("����:"); 
      for(int i = 0 ; i<attrCount ; i++) {
        System.out.println("  ������ : " + attr.getQName(i)); 
        System.out.println("  �������� : " + attr.getType(i)); 
        System.out.println("  ����ֵ: " + attr.getValue(i));       }    }     }
//Ԫ�ؽ����¼�������  
  public void endElement(String uri, String localName, String qname) {
    System.out.println("Ԫ�ؽ���: ������: " + localName + " �޶���: "  + qname + "  �����ռ�URI: "+uri);  }
   //�ı��¼�������  
  public void characters(char[] ch, int start, int length) {
    System.out.println("�ı��ַ�: " + new String(ch, start, length));      }
  //�հ��ַ��¼�������  
  public void ignorableWhitespace(char[] ch, int start, int length) {    System.out.println("���ԵĿհ�: " + length + " Characters.");       }  }
