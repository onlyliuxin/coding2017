package basic.dataStructure.stack.expr;

import org.apache.commons.lang3.StringUtils;

/**
 * 前序表达式
 */
public class PrefixExpr {
	String expr = null;

	/**
	 * +/*23-21*3-41
	 * @param expr
	 */
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		return new PostfixExpr(StringUtils.reverse(expr)).evaluate();
	}
}
