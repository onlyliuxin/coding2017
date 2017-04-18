package litestruts;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import static util.Print.*;

public class Configuration {

	Map<String, ActionCfg> actions = new HashMap<>();

	private static Configuration cfg = new Configuration();

	private Configuration() {

	}

	public static Configuration getNewInstance() {

		if (cfg == null) {
			cfg = new Configuration();
		}
		return cfg;
	}

	public void parse(String fileName) {

		String src = this.getClass().getPackage().getName();
		String filepath = src.replace(".", "/") + "/" + fileName;

		InputStream is = this.getClass().getResourceAsStream("/" + filepath);

		parseXML(is);

		try {
			is.close();
		} catch (IOException e) {

		}

	}

	public void parseXML(InputStream is) {

		SAXBuilder reader = new SAXBuilder();
		try {
			Document doc = reader.build(is);
			Element root = doc.getRootElement();

			for (Element element : root.getChildren("action")) {

				String actionName = element.getAttributeValue("name");
				String clz = element.getAttributeValue("class");
				ActionCfg ac = new ActionCfg(actionName, clz);

				for (Element e : element.getChildren("result")) {

					String result = e.getAttributeValue("name");
					String jsp = e.getText().trim();
					ac.addViewResult(result, jsp);
				}

				actions.put(actionName, ac);
			}

		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}

	public String getClassName(String action) {
		ActionCfg cfg = this.actions.get(action);
		if (cfg == null) {
			return null;
		}
		return cfg.getClassName();
	}

	public String getResultView(String action, String resultName) {
		ActionCfg cfg = this.actions.get(action);
		if (cfg == null) {
			return null;
		}
		return cfg.getViewResult().get(resultName);
	}

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.parse("struts.xml");
		String clz = cfg.getClassName("login");
		println(clz);

	}

	private static class ActionCfg {

		String name;
		String clz;
		Map<String, String> viewResult = new HashMap<>();

		public Map<String, String> getViewResult() {
			return viewResult;
		}

		public ActionCfg(String name, String clz) {
			this.name = name;
			this.clz = clz;
		}

		public void addViewResult(String result, String jsp) {
			viewResult.put(result, jsp);

		}

		public String getClassName() {
			return clz;
		}

	}

}
