/**
 * 
 */
package com.coderising.litestruts;

import java.util.HashMap;

/**
 * @author WANGCANCER
 *
 */
public class ActionConfig {
	 
	    private	String actionName;
	    private String clazzName;
	    private	HashMap<String, String> messageToresult=null;
		public String getActionName() {
			return actionName;
		}
		public void setActionName(String actionName) {
			this.actionName = actionName;
		}
		public String getClazzName() {
			return clazzName;
		}
		public void setClazzName(String clazzName) {
			this.clazzName = clazzName;
		}
		public HashMap<String, String> getMessageToresult() {
			return messageToresult;
		}
		public void setMessageToresult(HashMap<String, String> messageToresult) {
			this.messageToresult = messageToresult;
		}
		public ActionConfig(String actionName, String clazzName,
				HashMap<String, String> messageToresult) {
			super();
			this.actionName = actionName;
			this.clazzName = clazzName;
			this.messageToresult = messageToresult;
		}
		
	 
}
