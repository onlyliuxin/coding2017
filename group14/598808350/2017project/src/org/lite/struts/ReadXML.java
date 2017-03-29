package org.lite.struts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.comm.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 读取xml 配置文件
 * @author z
 *
 */
public class ReadXML {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String actionName = "login";
		HashMap map = readXml(actionName);
		
		Iterator it = map.keySet().iterator();
		StringUtil.printlnStr("it.hasNext():"+it.hasNext());
		while(it.hasNext()){
			String key = (String)it.next();
			String value = (String)map.get(key);
			
			StringUtil.printlnStr(key+":"+value);
		}
		
	}
	
	public static HashMap readXml(String actionName){
		HashMap map = new HashMap();
		String path = "E:\\zhoubf_code\\coding2017\\group14\\598808350\\2017project\\src\\org\\lite\\struts\\struts.xml";
		File f = new File(path);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbf.newDocumentBuilder();
			Document doc = dBuilder.parse(f);
			NodeList resultNL = doc.getElementsByTagName("action");
			for(int i=0;i<resultNL.getLength();i++){
				Node node = resultNL.item(i);
                Element  ele = (Element)node;  

                
				NamedNodeMap nodeMap = node.getAttributes();
				String name = nodeMap.getNamedItem("name").getNodeValue();//交易码
				if(actionName != null && actionName.equals(name)){
					String cla = nodeMap.getNamedItem("class").getNodeValue();//描述
					map.put("ActionName", name);
					map.put("ClassName", cla);
					
					
					NodeList childList = ele.getElementsByTagName("result");
					for(int j=0;j<childList.getLength();j++){
						Node childNode = childList.item(j);
						NamedNodeMap childNodeMap = childNode.getAttributes();
						String resultName = childNodeMap.getNamedItem("name").getNodeValue();
						String pageUrl = childNode.getTextContent();
						map.put(resultName, resultName);
						map.put(resultName+"URL", pageUrl);
					}
						
				}
				
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
		
		
		
		
		
		
	}

}
