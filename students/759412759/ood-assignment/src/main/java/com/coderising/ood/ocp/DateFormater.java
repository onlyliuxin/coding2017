package com.coderising.ood.ocp;

/**
 * 日期类型格式化模板
 * Created by Tudou on 2017/6/19.
 */
public class DateFormater extends Formater {

    @Override
    public String formatMessage(String message) {
        String txtDate = DateUtil.getCurrentDateAsString();
        return txtDate + " : " + message;
    }
}
