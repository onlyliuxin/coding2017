package cn.study2.myStruts;

import java.util.Map;
/*
  ��װaction�ڵ�
    <action name="login" class="LoginAction" method="login">
		<result name="success" type="redirect">/index.jsp</result>
		<result name="loginFaild">/login.jsp</result>
	</action>
 */
public class ActionMapping {
	// ����·������
		private String name;
		// ����aciton���ȫ��
		private String className;
		// ������
		private String method;
		// �����ͼ����
		private Map<String,Result> results;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		public Map<String, Result> getResults() {
			return results;
		}
		public void setResults(Map<String, Result> results) {
			this.results = results;
		}
}
