package litestruts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import static util.Print.*;
public class Configuration {

	
	ActionCfg actionCfg = new ActionCfg();
	
	private static Configuration cfg = new Configuration();
	
	private Configuration() {
		
	}
	
	public static Configuration getNewInstance() {
		
		if (cfg == null) {
			cfg = new Configuration();
		}
		return cfg;
	}
	
	private String getFile(String fileName) {
		
		String src = this.getClass().getPackage().getName();
		return "file://" + src + "\\" + fileName + ".xml";
	}
	
	public void parseAction(String src) {
		
		String fileName = getFile(src);
		SAXBuilder reader = new SAXBuilder();
	    try {
			Document doc = reader.build("C:\\struts.xml");
			Element root = doc.getRootElement();
			
			for(Element element : root.getChildren("action")) {
				
				String name = element.getAttributeValue("name");
				String clz = element.getAttributeValue("class");
				actionCfg.actionInfo.put(name, clz);
				
				for(Element e : element.getChildren("result")) {
					
					String result = e.getAttributeValue("name");
					String jsp = e.getText().trim();
					println("result:" + result + "jsp:" + jsp);
					Map<String,String> res = new HashMap<>();
					res.put(result, jsp);
					actionCfg.resultInfo.put(name, res);
				}
			}
			
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
 
	}

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.parseAction("struts");
		Map info = cfg.getActionInfo();
		Map result = cfg.getResultInfo().get("login");
		println(info);
		println(result);
	}

	
	private static class ActionCfg {
		
		private Map<String,String> actionInfo;
		private Map<String,Map<String,String>> resultInfo;
		public ActionCfg() {
			this.actionInfo = new HashMap<String,String>();
			this.resultInfo = new HashMap<String,Map<String,String>>();
		}
		
	}


	public Map<String, Map<String,String>> getResultInfo() {
		
		return actionCfg.resultInfo;
	}

	public Map<String, String> getActionInfo() {
		
		return actionCfg.actionInfo;
	}
	
	
	


}
