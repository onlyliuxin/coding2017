package main.week02.litestruts;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XmlSaxUtil {

	Map<String, ActionConfig> actions = new HashMap<>();

	public XmlSaxUtil(String fileName) {
		// 得到包名
		String packageName = this.getClass().getPackage().getName();
		// 把包名转化成路径的一部分
		packageName = packageName.replace('.', '/');
		// 基于流的sax方式解析，需要先给出流，从类的同地址下找文件。
		InputStream is = this.getClass().getResourceAsStream(
				"/" + packageName + "/" + fileName);

		parseXML(is);

		try {
			is.close();
		} catch (IOException e) {
			throw new CustomException(e);
		}
	}

	private void parseXML(InputStream is) {
		// 解析器工厂
		SAXBuilder builder = new SAXBuilder();

		try {
			// 获取xml文件对象
			Document doc = builder.build(is);
			// 根节点
			Element root = doc.getRootElement();

			for (Element actionElement : root.getChildren("action")) {

				String actionName = actionElement.getAttributeValue("name");
				String clzName = actionElement.getAttributeValue("class");

				ActionConfig ac = new ActionConfig(actionName, clzName);

				for (Element resultElement : actionElement
						.getChildren("result")) {

					String resultName = resultElement.getAttributeValue("name");
					String viewName = resultElement.getText().trim();

					ac.addViewResult(resultName, viewName);

				}

				this.actions.put(actionName, ac);
			}

		} catch (JDOMException e) {
			throw new CustomException(e);

		} catch (IOException e) {
			throw new CustomException(e);

		}

	}

	public String getClassName(String action) {
		ActionConfig ac = this.actions.get(action);
		if (ac == null) {
			return null;
		}
		return ac.getClassName();
	}

	public String getResultView(String action, String resultName) {
		ActionConfig ac = this.actions.get(action);
		if (ac == null) {
			return null;
		}
		return ac.getViewName(resultName);
	}

	private static class ActionConfig {

		String name;
		String clzName;
		Map<String, String> viewResult = new HashMap<>();

		public ActionConfig(String actionName, String clzName) {
			this.name = actionName;
			this.clzName = clzName;
		}

		public String getClassName() {
			return clzName;
		}

		public void addViewResult(String name, String viewName) {
			viewResult.put(name, viewName);
		}

		public String getViewName(String resultName) {
			return viewResult.get(resultName);
		}
	}

}
