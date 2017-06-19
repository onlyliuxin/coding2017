package edu.coerscnu.ood.ocp.logger.type;

import edu.coerscnu.ood.ocp.utils.DateUtil;

public class RawWithDate implements LogType{

	@Override
	public String getMsg() {
		return " " + DateUtil.getCurrentDate();
	}

}
